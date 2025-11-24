import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private String id;
    private String name;
    // Thuộc tính lương một ngày được bổ sung
    private double salaryPerDay; 

    public Employee(String id, String name, double salaryPerDay) {
        this.id = id;
        this.name = name;
        this.salaryPerDay = salaryPerDay;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalaryPerDay() { return salaryPerDay; } 
}

public class HRApp {

    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Tải dữ liệu mẫu khi khởi động để dễ thử nghiệm
        loadSampleData(); 
        
        int choice;
        do {
            System.out.println("\n===== PHẦN MỀM QUẢN LÝ NHÂN SỰ CÔNG TY =====");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Tính lương nhân viên theo mã");
            System.out.println("3. Danh sách nhân viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            try {
                // Đảm bảo loại bỏ khoảng trắng hoặc ký tự không mong muốn
                choice = Integer.parseInt(sc.nextLine().trim()); 
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    calculateSalary();
                    break;
                case 3:
                    listEmployees();
                    break;
                case 0:
                    System.out.println(" Thoát chương trình!");
                    break;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    // Tải dữ liệu mẫu để thử nghiệm nhanh
    private static void loadSampleData() {
        employees.add(new Employee("A001", "Nguyễn Văn Đức", 300000));
        employees.add(new Employee("A002", "Phạm Thị Huệ", 450000));
    }
    
    // --- CHỨC NĂNG 1: THÊM NHÂN VIÊN ---
    private static void addEmployee() {
        System.out.println("\n--- THÊM NHÂN VIÊN ---");
        System.out.print("Nhập mã nhân viên: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên nhân viên: ");
        String name = sc.nextLine();
        
        double salaryPerDay = 0;
        try {
            System.out.print("Nhập lương một ngày: ");
            salaryPerDay = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Lỗi nhập liệu: Lương một ngày phải là số. Thêm thất bại!");
            return;
        }

        employees.add(new Employee(id, name, salaryPerDay));
        System.out.println(" Đã thêm nhân viên!");
    }

    // --- CHỨC NĂNG 2: TÍNH LƯƠNG NHÂN VIÊN THEO MÃ ---
    private static void calculateSalary() {
        System.out.println("\n--- TÍNH LƯƠNG NHÂN VIÊN ---");
        System.out.print("Nhập mã nhân viên cần tính lương: ");
        String id = sc.nextLine();

        Employee found = null;
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id)) {
                found = e;
                break;
            }
        }

        if (found == null) {
            System.out.println(" Không tìm thấy nhân viên!");
            return;
        }

        double salaryPerDay = found.getSalaryPerDay();
        int days = 0;
        double advance = 0;

        try {
            System.out.print("Nhập số ngày làm việc: ");
            days = Integer.parseInt(sc.nextLine());
    
            System.out.printf("Lương một ngày cố định của %s là: %,.0f\n", found.getName(), salaryPerDay);
    
            System.out.print("Nhập số tiền tạm ứng (nhập 0 nếu không có): ");
            advance = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Lỗi nhập liệu: Số ngày và Tạm ứng phải là số. Tính lương thất bại!");
            return;
        }


        // Logic Tính toán Lương
        double totalSalary = days * salaryPerDay;
        double finalAmount = totalSalary - advance;

        System.out.println("\n------ KẾT QUẢ TÍNH LƯƠNG ------");
        System.out.println("Tên nhân viên: " + found.getName());
        System.out.println("Lương một ngày: " + String.format("%,.0f", salaryPerDay)); 
        System.out.println("Tổng lương: " + String.format("%,.0f", totalSalary));
        System.out.println("Tạm ứng: " + String.format("%,.0f", advance));
        System.out.println("Còn được lĩnh: " + String.format("%,.0f", finalAmount));
        System.out.println("--------------------------------");
    }

    // --- CHỨC NĂNG 3: DANH SÁCH NHÂN VIÊN ---
    private static void listEmployees() {
        System.out.println("\n===== DANH SÁCH NHÂN VIÊN =====");
        if (employees.isEmpty()) {
            System.out.println(" Chưa có nhân viên nào!");
            return;
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s |\n", "Mã NV", "Tên", "Lương/Ngày");
        System.out.println("-------------------------------------------------------");
        for (Employee e : employees) {
            System.out.printf("| %-10s | %-20s | %-15s |\n", 
                e.getId(), 
                e.getName(), 
                String.format("%,.0f", e.getSalaryPerDay())
            );
        }
        System.out.println("-------------------------------------------------------");
    }
}