package com.vn.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	//
	private String MSV, LastName, FirstName, Gender, Classs;
	private float MathsPoint, PhysicalPoint, ChemistryPoint, AveragePoint;

	public Student() {

	}

	public Student(String mSV, String lastName, String firstName, String gender, String classs, float mathsPoint,
			float physicalPoint, float chemistryPoint, float averagePoint) {
		MSV = mSV;
		LastName = lastName;
		FirstName = firstName;
		Gender = gender;
		Classs = classs;
		MathsPoint = mathsPoint;
		PhysicalPoint = physicalPoint;
		ChemistryPoint = chemistryPoint;
		AveragePoint = averagePoint;
	}

	public String getMSV() {
		return MSV;
	}

	public void setMSV(String mSV) {
		MSV = mSV;
	}


	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	// check lastname
	public boolean checkLastName(String lastName) {
		if (lastName != null && lastName.length() >= 2) {
			LastName = lastName;
			return true;
		} else {
			System.out.println("Nhập lại họ SV >= 2 kí tự!");
			return false;
		}
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	// check gender
	public boolean checktGender(String gender) {
		if (gender.equals("Nam") || gender.equals("Nữ")) {
			Gender = gender;
			return true;
		} else {
			System.out.println("Nhập lại giới tính: (Nam hoặc Nữ)");
			return false;
		}

	}

	public String getClasss() {
		return Classs;
	}

	public void setClasss(String classs) {
		Classs = classs;
	}

	// check class
	public boolean checkClasss(String classs) {
		if (classs.equals("CNTT") || classs.equals("CNPM") || classs.equals("HTTT")) {
			Classs = classs;
			return true;
		} else {
			System.out.println("Nhập lại lớp: (CNTT hoặc CNPM hoặc HTTT)");
			return false;
		}

	}

	public float getMathsPoint() {
		return MathsPoint;
	}

	public void setMathsPoint(float mathsPoint) {
		MathsPoint = mathsPoint;
	}

	//
	public boolean checkMathsPoint(float mathsPoint) {
		if (mathsPoint >= 0.0 && mathsPoint <= 10.0) {
			MathsPoint = mathsPoint;
			return true;
		} else {
			System.out.println("Nhập lại điểm (0.0 -> 10.0)");
			return false;
		}
	}

	public float getPhysicalPoint() {
		return PhysicalPoint;
	}

	public void setPhysicalPoint(float physicalPoint) {
		PhysicalPoint = physicalPoint;
	}

	//
	public boolean checkPhysicalPoint(float physicalPoint) {
		if (physicalPoint >= 0.0 && physicalPoint <= 10.0) {
			PhysicalPoint = physicalPoint;
			return true;
		} else {
			System.out.println("Nhập lại điểm (0.0 -> 10.0)");
			return false;
		}
	}

	public float getChemistryPoint() {
		return ChemistryPoint;
	}

	public void setChemistryPoint(float chemistryPoint) {
		ChemistryPoint = chemistryPoint;
	}

	//
	public boolean checkChemistryPoint(float chemistryPoint) {
		if (chemistryPoint >= 0.0 && chemistryPoint <= 10.0) {
			ChemistryPoint = chemistryPoint;
			return true;
		} else {
			System.out.println("Nhập lại điểm (0.0 -> 10.0)");
			return false;
		}
	}

	public float getAveragePoint() {
		return AveragePoint;
	}

	public void setAveragePoint(float averagePoint) {
		AveragePoint = (this.MathsPoint + this.PhysicalPoint + this.ChemistryPoint) / 3;
	}

	public float tinhdiemTB(float diemTB) {
		diemTB = (MathsPoint + ChemistryPoint + PhysicalPoint) / 3;
		return diemTB;
	}

	@Override
	public String toString() {
		return "Student [MSV=" + MSV + ", LastName=" + LastName + ", FirstName=" + FirstName + ", Gender=" + Gender
				+ ", Classs=" + Classs + ", MathsPoint=" + MathsPoint + ", PhysicalPoint=" + PhysicalPoint
				+ ", ChemistryPoint=" + ChemistryPoint + ", AveragePoint=" + AveragePoint + "]";
	}

	// sql
	static String hostName = "localhost";
	static String dbName = "studentmanagement";
	static String userName = "root";
	static String password = "123456";

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		// Khai báo class Driver cho DB MySQL
		// Việc này cần thiết với Java 5
		// Java6 tự động tìm kiếm Driver thích hợp.
		// Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
		// Class.forName("com.mysql.jdbc.Driver");
		// Cấu trúc URL Connection dành cho Oracle
		// Ví dụ: jdbc:mysql://localhost:3306/simplehr
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}

	public void input() throws SQLException {
		// mã sinh viên
		Scanner sca = new Scanner(System.in);
		int idsv = 0;

		Connection conn = null;
		try {
			conn = this.getMySQLConnection(hostName, dbName, userName, password);
			boolean checkidsv = true;
			do {
				System.out.println("Nhập mã sinh viên (kiểu số nguyên dương, tối đa 4 chữ số)");
				System.out.print("MSV-");
				try {
					idsv = Integer.parseInt(sca.nextLine().trim());//
					if (idsv > 0 && idsv <= 9) {
						this.MSV = "MSV-00" + idsv;
						System.out.println("Mã SV bạn vừa nhập là: " + MSV);
						checkidsv = true;
					} else if (idsv > 9 && idsv <= 99) {
						this.MSV = "MSV-0" + idsv;
						System.out.println("Mã SV bạn vừa nhập là: " + MSV);
						checkidsv = true;
					} else if (idsv > 99 && idsv <= 999) {
						this.MSV = "MSV-" + idsv;
						System.out.println("Mã SV bạn vừa nhập là: " + MSV);
						checkidsv = true;
					} else if (idsv > 999 && idsv <= 9999) {
						this.MSV = "MSV-" + idsv;
						System.out.println("Mã SV bạn vừa nhập là: " + MSV);
						checkidsv = true;
					} else {
						System.err.println("Bạn nhập chưa đúng định dạng Mã SV");
						checkidsv = false;
					}
					String sql = "select msv from studentmanagement.student where msv = '" + this.MSV + "';";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						System.err.println("Mã sinh viên đã tồn tại, mời bạn nhập lại");
						checkidsv = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					checkidsv = false;
					System.err.println("Bạn chưa nhập đúng định dạng Mã SV");

				}
			} while (!checkidsv);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
		}
		System.out.println("---------------------------------");

		// lastname
		boolean checklastname = true;
		do {
			try {// ko cần thiết
				System.out.println("Mời bạn nhập Họ và tên đệm của SV: ");
				this.LastName = sca.nextLine().trim();
				if (this.LastName.length() == 0) {
					System.err.println("Họ và tên đệm của SV không được để trống. ");
				} else if (this.LastName.length() > 50) {
					System.err.println("Họ và tên đệm của SV không được vượt quá 50 ký tự. ");
				}
				System.out.println("---------------------------------");
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Bạn nhập sai định dạng");
				checklastname = false;
			}
		} while (!checklastname || this.LastName.length() == 0 || this.LastName.length() > 50);
		// name
		boolean checkfirstname = true;
		do {
			try {
				System.out.println("Mời bạn nhập tên của SV: ");
				this.FirstName = sca.nextLine().trim();
				if (this.FirstName.length() == 0) {
					System.err.println("Tên của SV không được để trống. ");
				} else if (this.FirstName.length() > 50) {
					System.err.println("Tên của SV không được vượt quá 50 ký tự. ");
				}
				System.out.println("---------------------------------");
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Bạn nhập sai định dạng");
				checkfirstname = false;
			}
		} while (!checkfirstname || (this.FirstName.length() == 0 || this.FirstName.length() > 50));
		
		// gender
		do {
			System.out.println("Mời bạn nhập giới tính SV (Nam/ Nữ): ");
			Gender = sca.nextLine().trim();
		} while (!Gender.equalsIgnoreCase("Nam") && !Gender.equalsIgnoreCase("Nữ"));
		System.out.println("---------------------------------");
		
		// Class
		//boolean checkclass = true;
		do {
			System.out.println("Nhập lớp (CNTT/Kinh Tế/Kỹ Sư): ");
			this.Classs = sca.nextLine().trim();  //CNTT|
			System.out.println("Bạn vừa nhập: " + this.Classs);
		} while (!this.Classs.equalsIgnoreCase("CNTT") && !this.Classs.equalsIgnoreCase("CNPM")
				&& !this.Classs.equalsIgnoreCase("HTTT"));
		System.out.println("---------------------------------");
		// Math
		boolean checkmath = true;
		do {
			System.out.println("Nhập điểm toán (0.0 -> 10.0)");
			try {
				this.MathsPoint = Float.parseFloat(sca.nextLine().trim());
				System.out.println("Bạn vừa nhập: " + this.MathsPoint);
				if (this.MathsPoint >= 0.0f && this.MathsPoint <= 10.0f) {
					checkmath = true;
					System.out.println("Thành công!");
				} else {
					System.err.println("Bạn nhập sai định dạng!");
					checkmath = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Bạn nhập sai định dạng!");
				checkmath = false;
			}
		} while (!checkmath);
		System.out.println("---------------------------------");
		// Physical
		boolean checkphysical = true;
		do {
			System.out.println("Nhập điểm lý (0.0 -> 10.0)");
			try {
				this.PhysicalPoint = Float.parseFloat(sca.nextLine().trim());
				System.out.println("Bạn vừa nhập: " + this.PhysicalPoint);
				if (this.PhysicalPoint >= 0.0f && this.PhysicalPoint <= 10.0f) {
					checkphysical = true;
					System.out.println("Thành công!");
				} else {
					System.err.println("Bạn nhập sai định dạng!");
					checkphysical = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Bạn nhập sai định dạng!");
				checkphysical = false;
			}
		} while (!checkphysical);
		System.out.println("---------------------------------");

		// Chemistry
		boolean checkchemistry = true;
		do {
			System.out.println("Nhập điểm Hoá (0.0 -> 10.0)");
			try {
				this.ChemistryPoint = Float.parseFloat(sca.nextLine().trim());
				System.out.println("Bạn vừa nhập: " + this.ChemistryPoint);
				if (this.ChemistryPoint >= 0.0f && this.ChemistryPoint <= 10.0f) {
					checkchemistry = true;
					System.out.println("Thành công!");
				} else {
					System.err.println("Bạn nhập sai định dạng!");
					checkchemistry = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Bạn nhập sai định dạng!");
				checkchemistry = false;
			}
		} while (!checkchemistry);
		System.out.println("---------------------------------");

		// average
		System.out.println(AveragePoint = (this.MathsPoint + this.PhysicalPoint + this.ChemistryPoint) / 3);
	}

	public void display() {
		System.out.println(this);
	}
}
