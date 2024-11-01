package model;

import java.util.Date;
import java.util.Objects;

public class EmployeeCard {
    private int empId; // 직원 ID / 従業員ID
    private String userName; // 사용자 이름 / ユーザー名
    private String email; // 이메일 / メールアドレス
    private String phoneNumber; // 전화번호 / 電話番号
    private String employmentType; // 고용 형태 / 雇用形態
    private String dept; // 부서 / 部門
    private String position; // 직위 / 職位
    private Date retirementDate; // 퇴직일 / 退職日
    private String retirementReason; // 퇴직 이유 / 退職理由
    private double retirementPayment; // 퇴직금 / 退職金
    private String employmentStatus; // 고용 상태 / 雇用状況
    private Date hireDate; // 고용일 / 採用日

    // 생성자 / コンストラクタ
    public EmployeeCard(int empId, String userName, String email, String phoneNumber, String employmentType,
                        String dept, String position, Date retirementDate, String retirementReason,
                        double retirementPayment, String employmentStatus, Date hireDate) {
        this.empId = empId; // 직원 ID 설정 / 従業員IDを設定
        this.userName = userName; // 사용자 이름 설정 / ユーザー名を設定
        this.email = email; // 이메일 설정 / メールアドレスを設定
        this.phoneNumber = phoneNumber; // 전화번호 설정 / 電話番号を設定
        this.employmentType = employmentType; // 고용 형태 설정 / 雇用形態を設定
        this.dept = dept; // 부서 설정 / 部門を設定
        this.position = position; // 직위 설정 / 職位を設定
        this.retirementDate = retirementDate; // 퇴직일 설정 / 退職日を設定
        this.retirementReason = retirementReason; // 퇴직 이유 설정 / 退職理由を設定
        this.retirementPayment = retirementPayment; // 퇴직금 설정 / 退職金を設定
        this.employmentStatus = employmentStatus; // 고용 상태 설정 / 雇用状況を設定
        this.hireDate = hireDate; // 고용일 설정 / 採用日を設定
    }

    // Getter 메서드들 / ゲッター メソッド
    public int getEmpId() { return empId; } // 직원 ID 반환 / 従業員IDを返す
    public String getUserName() { return userName; } // 사용자 이름 반환 / ユーザー名を返す
    public String getEmail() { return email; } // 이메일 반환 / メールアドレスを返す
    public String getPhoneNumber() { return phoneNumber; } // 전화번호 반환 / 電話番号を返す
    public String getEmploymentType() { return employmentType; } // 고용 형태 반환 / 雇用形態を返す
    public String getDept() { return dept; } // 부서 반환 / 部門を返す
    public String getPosition() { return position; } // 직위 반환 / 職位を返す
    public Date getRetirementDate() { return retirementDate; } // 퇴직일 반환 / 退職日を返す
    public String getRetirementReason() { return retirementReason; } // 퇴직 이유 반환 / 退職理由を返す
    public double getRetirementPayment() { return retirementPayment; } // 퇴직금 반환 / 退職金を返す
    public String getEmploymentStatus() { return employmentStatus; } // 고용 상태 반환 / 雇用状況を返す
    public Date getHireDate() { return hireDate; } // 고용일 반환 / 採用日を返す

    // Setter 메서드들 / セッター メソッド
    public void setEmpId(int empId) { this.empId = empId; } // 직원 ID 설정 / 従業員IDを設定
    public void setUserName(String userName) { this.userName = userName; } // 사용자 이름 설정 / ユーザー名を設定
    public void setEmail(String email) { this.email = email; } // 이메일 설정 / メールアドレスを設定
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; } // 전화번호 설정 / 電話番号を設定
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; } // 고용 형태 설정 / 雇用形態を設定
    public void setDept(String dept) { this.dept = dept; } // 부서 설정 / 部門を設定
    public void setPosition(String position) { this.position = position; } // 직위 설정 / 職位を設定
    public void setRetirementDate(Date retirementDate) { this.retirementDate = retirementDate; } // 퇴직일 설정 / 退職日を設定
    public void setRetirementReason(String retirementReason) { this.retirementReason = retirementReason; } // 퇴직 이유 설정 / 退職理由を設定
    public void setRetirementPayment(double retirementPayment) { this.retirementPayment = retirementPayment; } // 퇴직금 설정 / 退職金を設定
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; } // 고용 상태 설정 / 雇用状況を設定
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; } // 고용일 설정 / 採用日を設定

    @Override
    public String toString() {
        // 객체 상태 반환 / オブジェクトの状態を返す
        return "EmployeeCard{" +
                "empId=" + empId + // 직원 ID / 従業員ID
                ", userName='" + userName + '\'' + // 사용자 이름 / ユーザー名
                ", email='" + email + '\'' + // 이메일 / メールアドレス
                ", phoneNumber='" + phoneNumber + '\'' + // 전화번호 / 電話番号
                ", employmentType='" + employmentType + '\'' + // 고용 형태 / 雇用形態
                ", dept='" + dept + '\'' + // 부서 / 部門
                ", position='" + position + '\'' + // 직위 / 職位
                ", retirementDate=" + retirementDate + // 퇴직일 / 退職日
                ", retirementReason='" + retirementReason + '\'' + // 퇴직 이유 / 退職理由
                ", retirementPayment=" + retirementPayment + // 퇴직금 / 退職金
                ", employmentStatus='" + employmentStatus + '\'' + // 고용 상태 / 雇用状況
                ", hireDate=" + hireDate + // 고용일 / 採用日
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // 객체 동등성 비교 / オブジェクトの等価性を比較
        if (this == o) return true; // 동일 객체인지 확인 / 同一オブジェクトか確認
        if (!(o instanceof EmployeeCard)) return false; // EmployeeCard 인스턴스인지 확인 / EmployeeCardインスタンスか確認
        EmployeeCard that = (EmployeeCard) o; // 해당 객체를 EmployeeCard로 변환 / 該当オブジェクトをEmployeeCardに変換
        return empId == that.empId; // 직원 ID 비교 / 従業員IDを比較
    }

    @Override
    public int hashCode() {
        // 해시 코드 반환 / ハッシュコードを返す
        return Objects.hash(empId); // 직원 ID로 해시 코드 생성 / 従業員IDでハッシュコードを生成
    }
}
