```sql
CREATE OR REPLACE PROCEDURE insert_transaction(
    p_account_id bigint,
    p_amount numeric(15,2),
    p_transaction_date timestamp with time zone
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_old_balance numeric(15,2);
    v_new_balance numeric(15,2);
BEGIN
    BEGIN
        SELECT balance INTO v_old_balance
        FROM accounts
        WHERE account_id = p_account_id
        FOR UPDATE;
        
        v_new_balance := v_old_balance + p_amount;
        
        UPDATE accounts
        SET balance = v_new_balance
        WHERE account_id = p_account_id;
        
        INSERT INTO transactions (account_id, amount, transaction_date)
        VALUES (p_account_id, p_amount, p_transaction_date);
        
        INSERT INTO audit_log (transaction_id, account_id, old_balance, new_balance, change_date)
        VALUES (currval(pg_get_serial_sequence('transactions', 'transaction_id')), p_account_id, v_old_balance, v_new_balance, p_transaction_date);
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;
END;
$$;

```