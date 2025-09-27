package com.t2404e.wellcometojavaweb;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.repository.AccountRepository;
import com.t2404e.wellcometojavaweb.repository.MySQLAccountRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final AccountRepository repo = new MySQLAccountRepository();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm account");
            System.out.println("2. Xem tất cả account");
            System.out.println("3. Tìm account theo ID");
            System.out.println("4. Xóa account theo ID");
            System.out.println("5. Cập nhật account");
            System.out.println("0. Thoát");
            int c = readInt("Chọn: ");

            switch (c) {
                case 1 -> addAccount();
                case 2 -> listAllAccounts();
                case 3 -> findAccById();
                case 4 -> deleteAccById();
                case 5 -> updateAccById();
                case 0 -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addAccount() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        int s = readInt("Status: ");
        repo.add(new Account(u, p, s));
    }

    private static void listAllAccounts() {
        List<Account> list = repo.findAll();
        if (list.isEmpty()) {
            System.out.println("Không có account nào.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void findAccById() {
        System.out.print("Nhập ID: ");
        long id = Long.parseLong(sc.nextLine());
        Account acc = repo.findById(id);
        if (acc != null) System.out.println(acc);
        else System.out.println("Không tìm thấy!");
    }

    private static void deleteAccById() {
        System.out.print("Nhập ID cần xóa: ");
        long id = Long.parseLong(sc.nextLine());
        repo.delete(id);
    }

    private static void updateAccById() {
        System.out.print("Nhập ID cần update: ");
        long id = Long.parseLong(sc.nextLine());
        Account acc = repo.findById(id);
        if (acc == null) {
            System.out.println("Không tìm thấy account.");
            return;
        }

        System.out.println("Account cũ: " + acc);

        System.out.print("Username mới (Enter để giữ nguyên): ");
        String u = sc.nextLine();
        if (u.isEmpty()) u = acc.getUsername();

        System.out.print("Password mới (Enter để giữ nguyên): ");
        String p = sc.nextLine();
        if (p.isEmpty()) p = acc.getPasswordHash();

        System.out.print("Status mới (Enter để giữ nguyên): ");
        String sInput = sc.nextLine();
        int s = sInput.isEmpty() ? acc.getStatus() : Integer.parseInt(sInput);

        Account updated = new Account(u, p, s);

        // Hỏi xác nhận
        System.out.println("\nXác nhận cập nhật:");
        System.out.println("Username: " + u);
        System.out.println("Password: " + p);
        System.out.println("Status: " + s);
        System.out.print("Bạn có chắc muốn cập nhật? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            repo.update(id, updated);
        } else {
            System.out.println("Đã hủy cập nhật.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
