import java.util.Scanner; // Scanner sınıfını ekledik

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Kayıt Ol");
            System.out.println("2. Giriş Yap");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Güle güle!");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void register() {
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        if (bank.register(username, password)) {
            System.out.println("Kayıt başarılı!");
        } else {
            System.out.println("Bu kullanıcı adı zaten var.");
        }
    }

    private void login() {
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        Account acc = bank.login(username, password);
        if (acc != null) {
            System.out.println("Giriş başarılı!");
            accountMenu(acc);
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre.");
        }
    }

    private void accountMenu(Account acc) {
        while (true) {
            System.out.println("\n1. Bakiye Görüntüle");
            System.out.println("2. Para Yatır");
            System.out.println("3. Para Çek");
            System.out.println("4. Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Bakiye: " + acc.getBalance());
                case 2 -> {
                    System.out.print("Yatırılacak miktar: ");
                    double amt = scanner.nextDouble();
                    acc.deposit(amt);
                    System.out.println("Para yatırıldı.");
                }
                case 3 -> {
                    System.out.print("Çekilecek miktar: ");
                    double amt = scanner.nextDouble();
                    if (acc.withdraw(amt)) {
                        System.out.println("Para çekildi.");
                    } else {
                        System.out.println("Yetersiz bakiye.");
                    }
                }
                case 4 -> {
                    System.out.println("Çıkış yapılıyor...");
                    return; // Çıkış yapmak için
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }
}
