import java.util.concurrent.*;  

class Account {
    private String number;  
    private double balance;   
    
    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }
    
    public String getNumber() {
        return number;
    }
    
    public synchronized double getBalance() {
        return balance;
    }
    
    public synchronized void deposit(double amount) {
        balance += amount;
    }
    
    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public synchronized boolean transferTo(Account destination, double amount) {
        if (this.withdraw(amount)) {
            destination.deposit(amount);
            return true;
        }
        return false;
    }
}

class Bank {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();
    
    public void addAccount(Account account) {
        accounts.put(account.getNumber(), account);
    }
    
    public boolean transfer(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        Account source = accounts.get(sourceAccountNumber);
        Account destination = accounts.get(destinationAccountNumber);

        if (source != null && destination != null) {
            return source.transferTo(destination, amount);
        }
        return false;
    }
}

public class BankSimulator {
    public static void main(String[] args) {
        Account account1 = new Account("123", 1000.0);
        Account account2 = new Account("456", 1500.0);
        Account account3 = new Account("789", 2000.0);
        
        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        executor.submit(() -> {
            boolean success = bank.transfer("123", "456", 200.0);
            System.out.println("Transfer of 200.0 from 123 to 456: " + (success ? "Success" : "Failure"));
        });

        executor.submit(() -> {
            boolean success = bank.transfer("456", "789", 500.0);
            System.out.println("Transfer of 500.0 from 456 to 789: " + (success ? "Success" : "Failure"));
        });

        executor.submit(() -> {
            boolean success = bank.transfer("123", "789", 100.0);
            System.out.println("Transfer of 100.0 from 123 to 789: " + (success ? "Success" : "Failure"));
        });
        
        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        
        System.out.println("Balance of account 123: " + account1.getBalance());
        System.out.println("Balance of account 456: " + account2.getBalance());
        System.out.println("Balance of account 789: " + account3.getBalance());
    }
}
