CREATE OR REPLACE FUNCTION after_transaction_insert()
RETURNS TRIGGER AS $$
DECLARE
    v_old_balance numeric(15,2);
    v_new_balance numeric(15,2);
BEGIN
    SELECT balance INTO v_old_balance
    FROM accounts
    WHERE account_id = NEW.account_id
    FOR UPDATE;
    
    v_new_balance := v_old_balance + NEW.amount;
    
    UPDATE accounts
    SET balance = v_new_balance
    WHERE account_id = NEW.account_id;
    
    INSERT INTO audit_log (transaction_id, account_id, old_balance, new_balance, change_date)
    VALUES (NEW.transaction_id, NEW.account_id, v_old_balance, v_new_balance, NEW.transaction_date);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_after_transaction_insert
AFTER INSERT ON transactions
FOR EACH ROW
EXECUTE FUNCTION after_transaction_insert();
