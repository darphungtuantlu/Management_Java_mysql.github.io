/**
 * 
 */
package com.vn.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxSession.AuthenticateContinue;

/**
 * @author Admin
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static String hostName = "localhost";
	static String dbName = "studentmanagement";
	static String userName = "root";
	static String password = "123456";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int chonChucNang = 0;
		Scanner sca = new Scanner(System.in);
		do {
			System.out.println("*********************************************");
			hienthiMenu();
			System.out.println("*********************************************");
			System.out.println("Hãy chọn 1 chức năng: ");

			while (true) {
				try {
					chonChucNang = Integer.parseInt(sca.nextLine().trim());
					System.out.println("Bạn chọn chức năng: " + chonChucNang);
					break;
				} catch (NumberFormatException e) {
					System.err.println("Bạn phải nhập số có trong Menu!");
				}
			}
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			Connection conn = null;
			boolean tieptuc = true; // kiểm tra có continue hay k
			switch (chonChucNang) {
			case 1:
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);
						//
						if (conn != null) {
							System.out.println("connect success");
							String sql = "";
							sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = conn.prepareStatement(sql);
							ResultSet resultSet = preperStatement.executeQuery();
							if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) {
								System.out.println("Chưa có bản ghi nào");
							} else {
								String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
								System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
										"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
								while (resultSet.next()) {
									System.out.println(
											"-------------------------------------------------------------------------------------------------------------------------");
									format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, resultSet.getString("MSV"),
											resultSet.getString("LastName"), resultSet.getString("FirstName"),
											resultSet.getString("Gender"), resultSet.getString("Classs"),
											resultSet.getFloat("MathsPoint"), resultSet.getFloat("PhysicalPoint"),
											resultSet.getFloat("ChemistryPoint"), resultSet.getFloat("averagePoint"));
									// System.out.println(
									// "MSV \t LastName \t FirstName \t Gender \t Class \t Math \t Physical \t
									// Chemistry \t Average");
									// System.out.print(resultSet.getString("MSV") + " \t ");
									// System.out.print(resultSet.getString("LastName") + " \t ");
									// System.out.print(resultSet.getString("FirstName") + " \t\t ");
									// System.out.print(resultSet.getString("Gender") + " \t\t ");
									// System.out.print(resultSet.getString("Classs") + " \t ");
									// System.out.print(resultSet.getFloat("MathsPoint") + "\t ");
									// System.out.print(resultSet.getFloat("PhysicalPoint") + "\t\t ");
									// System.out.print(resultSet.getFloat("ChemistryPoint") + "\t\t ");
									// System.out.println(resultSet.getFloat("averagePoint"));
									// System.out.println(
									// "----------------------------------------------------------------------------------------------------------------");
								}

							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 2: //2
				tieptuc = true; // kiểm tra có continue hay k
				do {
					conn = DriverManager.getConnection(connectionURL, userName, password);
					//
					if (conn != null) {
						System.out.println("connect success");
//							// nhap số sv
						int numbersv = 0;
						boolean checknumbersv = true;
//						do {
//							System.out.print("Nhập số sinh viên muốn thêm: "); // nên giới hạn số lượng nhập vào!
//							try {
//								numbersv = Integer.parseInt(sca.nextLine().trim());
//								if (numbersv < 0) {
//									checknumbersv = false;
//								} else if (numbersv == 0) {
//									System.out.println("Bạn nhập 0 ?");
//									break;
//								} else {
//									checknumbersv = true;
//								}
//
//							} catch (Exception e) {
//								// TODO: handle exception
//								System.err.println("Bạn nhập sai định dạng!");
//								checknumbersv = false;
//							}
//						} while (!checknumbersv);
						
						while (true) {
							try {
								System.out.println("Nhập số sinh viên cần thêm: (1->10)");
								numbersv = Integer.parseInt(sca.nextLine().trim());
								if(numbersv > 0 && numbersv <11) {
									break;
								} else {
									System.out.println("Vui lòng chọn số lượng sinh viên cân thêm hợp lệ");
								}
									
							} catch (NumberFormatException e) {
								System.err.println("Bạn phải nhập số hợp lệ!");
							}
						}
						// 

						for (int i = 0; i < numbersv; i++) {
							conn = DriverManager.getConnection(connectionURL, userName, password);
							System.out.println("Nhập thông tin của sinh viên thứ: " + (i + 1));
							Student student = new Student();
							student.input();
							try {
								String sql = "";
								sql = "insert into studentmanagement.student value (?, ?, ?, ?,?, ?, ?, ?, ?);";
								PreparedStatement preperStatement = conn.prepareStatement(sql);
								preperStatement = conn.prepareStatement(sql);
								preperStatement.setString(1, student.getMSV());
								preperStatement.setString(2, student.getLastName());
								preperStatement.setString(3, student.getFirstName());
								preperStatement.setString(4, student.getGender());
								preperStatement.setString(5, student.getClasss());
								preperStatement.setFloat(6, student.getMathsPoint());
								preperStatement.setFloat(7, student.getPhysicalPoint());
								preperStatement.setFloat(8, student.getChemistryPoint());
								preperStatement.setFloat(9, ((student.getMathsPoint() + student.getPhysicalPoint()
										+ student.getChemistryPoint()) / 3));
								int rowAffected = preperStatement.executeUpdate();
								if (rowAffected != 0) {
									System.out.println("Thêm thành công!");
									System.out.println("---------------------------------------");
								}

							} catch (SQLException e) {
								// TODO: handle exception
							} finally {
								conn.close();
							}
						}
					} else {
						System.out.println("k thành công");
					}

					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 3: // 3
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								int idsv = 0;
								boolean checkidsv = true;
								Student student = new Student();
								String msv;
								do {
									System.out.println(
											"Nhập mã sinh viên cần chỉnh sửa (kiểu số nguyên dương, tối đa 4 chữ số):");
									System.out.print("MSV-");
									try {
										idsv = Integer.parseInt(sca.nextLine().trim());

										if (idsv > 0 && idsv <= 9) {
											msv = "MSV-00" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 9 && idsv <= 99) {
											msv = "MSV-0" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 99 && idsv <= 999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 999 && idsv <= 9999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											System.out.println("Mã SV bạn vừa nhập là: " + student.getMSV());
											checkidsv = true;
										} else {
											System.err.println("Bạn nhập chưa đúng định dạng Mã SV");
											checkidsv = false;
										}
										// Kiểm tra msv k tồn tại
										sql = "select * from student where msv = '" + student.getMSV() + "' ; ";
										rs = preperStatement.executeQuery(sql);
										if (!rs.next()) {
											System.err.println("Mã sinh viên không tồn tại, mời bạn nhập lại");
											// conn.close();
											checkidsv = false;
										}
									} catch (Exception e) {
										System.err.println("Bạn chưa nhập đúng định dạng Mã SV");
										checkidsv = false;
									}
								} while (!checkidsv);
								// làm tiếp sau khi chọn đc mã sv
								// Chọn thông tin cần chỉnh sửa
								boolean checkchose = true;
								int chose = 0;
								String YorN; // biến Y or N để xác nhận tiếp tục chỉnh sửa mã sinh viên
								do {
									// do {// thực hiện sửa trường msv này tiếp
									// Lấy giá trị điểm toán, lý, hóa trước khi chỉnh sửa điểm
									String sql2 = "select * from studentmanagement.student where msv = '"
											+ student.getMSV() + "';";
									ResultSet rs2 = preperStatement.executeQuery(sql2);
									float toancu = 0f;
									float lycu = 0f;
									float hoacu = 0f;
									while (rs2.next()) {
										toancu = rs2.getFloat("mathspoint");
										System.out.println(toancu);
										lycu = rs2.getFloat("physicalpoint");
										System.out.println(lycu);
										hoacu = rs2.getFloat("chemistrypoint");// dm
										System.out.println(hoacu);
									}
									do {
										System.out.println(
												"1 - LastName, 2 - FirstName, 3 - Giới tính, 4 - Lớp, 5 - Điểm Toán, 6 - Điểm Lý, 7 - Điểm Hóa");
										System.out.println("Mời bạn chọn thông tin cần chỉnh sửa (Nhập 1-7):");
										try {
											chose = Integer.parseInt(sca.nextLine().trim());
											if (chose > 0 && chose < 8) {
												checkchose = true;
											} else {
												System.err.println("Vui lòng chọn thông tin cần chỉnh sửa phù hợp!");
												checkchose = false;
											}
										} catch (Exception e) {
											System.err.println("Vui lòng chọn thông tin cần chỉnh sửa phù hợp!");
											checkchose = false;
										}
									} while (!checkchose);
									// Chỉnh sửa thông tin NSD lựa chọn:
									boolean check = true; // biến kiểm tra
									switch (chose) {
									case 1: { // update lastname
										do {
											System.out.println("Mời bạn nhập Lastname mới:");
											String lastName = sca.nextLine().trim();
											student.setLastName(lastName);
											if (student.getLastName().length() == 0) {
												System.err.println("Họ và tên đệm của SV không được để trống. ");
											}else if (student.getLastName().length() > 50) {
												System.err.println("Họ và tên đệm của SV không được vượt quá 50 ký tự. ");
											}
										} while (student.getLastName().length() == 0);

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateLastName = "update studentmanagement.student SET lastname = '"
												+ student.getLastName() + "' where msv = '" + student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateLastName);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công ");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 2: {
										do {
											System.out.println("Mời bạn nhập Firstname mới:");
											String firstName = sca.nextLine().trim();
											student.setFirstName(firstName);
											if (student.getFirstName().length() == 0) {
												System.err.println("Tên của SV không được để trống. ");
											}else if (student.getFirstName().length() > 50) {
												System.err.println("Tên của SV không được vượt quá 50 ký tự. ");
											}
							
										} while (student.getFirstName().length() == 0);

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateFirstName = "update student SET firstname = '"
												+ student.getFirstName() + "' where msv = '" + student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateFirstName);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công ");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 3: { // update gender
										do {
											System.out.println("Mời bạn nhập giới tính mới: (Nam/Nữ)");
											String Gender = sca.nextLine().trim();
											student.setGender(Gender);
											if (!student.getGender().equalsIgnoreCase("Nam")
													&& !student.getGender().equalsIgnoreCase("Nữ")) {
												System.out.println("Vui lòng nhập lại (Nam/Nữ)");
											}
										} while (!student.getGender().equalsIgnoreCase("Nam")
												&& !student.getGender().equalsIgnoreCase("Nữ"));

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateGender = "update student SET gender = '" + student.getGender()
												+ "' where msv = '" + student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateGender);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 4: {
										do {
											System.out.println("Mời bạn nhập Lớp mới (HTTT/CNPM/CNTT): ");
											String classs = sca.nextLine().trim();
											student.setClasss(classs);
											if (!student.getClasss().equalsIgnoreCase("HTTT")
													&& !student.getClasss().equalsIgnoreCase("CNPM")
													&& !student.getClasss().equalsIgnoreCase("CNTT")) {
												System.out.println("Vui lòng nhập lại (HTTT/CNPM/CNTT)");
											}
										} while (!student.getClasss().equalsIgnoreCase("HTTT")
												&& !student.getClasss().equalsIgnoreCase("CNPM")
												&& !student.getClasss().equalsIgnoreCase("CNTT"));

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateClass = "update student SET classs = '" + student.getClasss()
												+ "' where msv = '" + student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateClass);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 5: {
										do {
											check = true;
											try {
												System.out.println("Mời bạn nhập điểm Toán mới (0.0 -> 10.0): ");
												float mathsPoint = Float.parseFloat(sca.nextLine().trim());
												student.setMathsPoint(mathsPoint);
												if (student.getMathsPoint() > 0.0 && student.getMathsPoint() <= 10.0) {
													check = true;
												} else {
													System.err.println("Bạn nhập chưa đúng định dạng điểm.");
													check = false;
												}
											} catch (Exception e) {
												System.err.println("Bạn nhập chưa đúng định dạng điểm.");
												check = false;
											}
										} while (!check);

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateMathsPoint = "update student SET mathspoint = "
												+ student.getMathsPoint() + ", averagePoint = "
												+ (student.getMathsPoint() + lycu + hoacu) / 3 + "where msv = '"
												+ student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateMathsPoint);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công ");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 6: {
										do {
											check = true;
											try {
												System.out.println("Mời bạn nhập điểm Lý mới (0.0 -> 10.0): ");
												float physialPoint = Float.parseFloat(sca.nextLine().trim());
												student.setPhysicalPoint(physialPoint);
												if (student.getPhysicalPoint() > 0.0
														&& student.getPhysicalPoint() <= 10.0) {
													check = true;
												} else {
													System.err.println("Bạn nhập chưa đúng định dạng điểm.");
													check = false;
												}
											} catch (Exception e) {
												System.err.println("Bạn nhập chưa đúng định dạng điểm.");
												check = false;
											}
										} while (!check);

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdatePhysicalPoint = "update student SET physicalpoint = "
												+ student.getPhysicalPoint() + ", averagePoint = "
												+ (student.getPhysicalPoint() + toancu + hoacu) / 3 + "where msv = '"
												+ student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdatePhysicalPoint);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công ");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									case 7: {
										do {
											check = true;
											try {
												System.out.println("Mời bạn nhập điểm Hóa mới (0.0 -> 10.0): ");
												float chemistryPoint = Float.parseFloat(sca.nextLine().trim());
												student.setChemistryPoint(chemistryPoint);
												if (student.getChemistryPoint() > 0.0
														&& student.getChemistryPoint() <= 10.0) {
													check = true;
												} else {
													System.err.println("Bạn nhập chưa đúng định dạng điểm.");
													check = false;
												}
											} catch (Exception e) {
												System.err.println("Bạn nhập chưa đúng định dạng điểm.");
												check = false;
											}
										} while (!check);

										// Update giá trị mới thay thế giá trị cũ
										String sqlUpdateChemistryPoint = "update student SET chemistrypoint = "
												+ student.getChemistryPoint() + ", averagePoint = "
												+ (student.getChemistryPoint() + lycu + toancu) / 3 + "where msv = '"
												+ student.getMSV() + "'";
										int countRow = preperStatement.executeUpdate(sqlUpdateChemistryPoint);
										if (countRow > 0) {
											System.out.println("Chỉnh sửa thành công ");
											System.out.println("Số lượng bản ghi thay đổi: " + countRow);
										}
										break;
									}
									}// ngoặc switch

									do {
										System.out.println(
												"-------------------------------------------------------------------------");
										System.out.println(
												"Tiếp tục chỉnh sửa mã sinh viên: " + student.getMSV() + "? (Y/N)");
										YorN = sca.nextLine().trim();
									} while (!YorN.equalsIgnoreCase("Y") && !YorN.equalsIgnoreCase("N"));
								} while (YorN.equalsIgnoreCase("Y"));
							}
						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {
						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 4: // 4
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								int idsv = 0;
								boolean checkidsv = true;
								Student student = new Student();
								String msv;
								do {
									System.out.println(
											"Nhập mã sinh viên cần chỉnh sửa (kiểu số nguyên dương, tối đa 4 chữ số):");
									System.out.print("MSV-");
									try {
										idsv = Integer.parseInt(sca.nextLine().trim());

										if (idsv > 0 && idsv <= 9) {
											msv = "MSV-00" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 9 && idsv <= 99) {
											msv = "MSV-0" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 99 && idsv <= 999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 999 && idsv <= 9999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											System.out.println("Mã SV bạn vừa nhập là: " + student.getMSV());
											checkidsv = true;
										} else {
											System.err.println("Bạn nhập chưa đúng định dạng Mã SV");
											checkidsv = false;
										}
										// Kiểm tra msv k tồn tại
										sql = "select * from student where msv = '" + student.getMSV() + "' ; ";
										rs = preperStatement.executeQuery(sql);
										if (!rs.next()) {
											System.err.println("Mã sinh viên không tồn tại, mời bạn nhập lại");
											// conn.close();
											checkidsv = false;
										}
									} catch (Exception e) {
										System.err.println("Bạn chưa nhập đúng định dạng Mã SV");
										checkidsv = false;
									}
								} while (!checkidsv);
								// làm sau khi check msv
								// Tìm kiếm và hiển thị thông tin SV theo Mã SV:
								sql = "select * from student where msv = '" + student.getMSV() + "'";
								rs = preperStatement.executeQuery(sql);
								
								String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
								System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
										"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
								while (rs.next()) {
									System.out.println(
											"-------------------------------------------------------------------------------------------------------------------------");
									format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
											rs.getString("FirstName"), rs.getString("Gender"), rs.getString("Classs"),
											rs.getFloat("MathsPoint"), rs.getFloat("PhysicalPoint"),
											rs.getFloat("ChemistryPoint"), rs.getFloat("averagePoint"));

								}
							}
						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 5: // 5
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								boolean check = true;
								Student student = new Student();
								int checkTonTaiFirstName = 0;
								do {

									do {
										try {
											System.out.println("Nhập tên sinh viên cần tìm kiếm: ");
											String firstName = sca.nextLine().trim();
											student.setFirstName(firstName);
											if (student.getFirstName().length() == 0) {
												System.err.println("Tên của SV không được để trống. ");
											} else if (student.getFirstName().length() > 50) {
												System.err.println("Tên của SV vượt quá 50 ký tự ");
											}
										} catch (Exception e) {
											// TODO: handle exception
											System.err.println("Bạn nhập sai định dạng");
											check = false;
										}
									} while (!check || (student.getFirstName().length() == 0
											|| student.getFirstName().length() > 50));
									// Kiểm tra có tồn tại hay không:
									sql = "select * from student where firstname like '" + student.getFirstName()
											+ "%' ";
									rs = preperStatement.executeQuery(sql);
									while (rs.next()) {
										checkTonTaiFirstName++;
									}
									if (checkTonTaiFirstName == 0) {
										System.err.println("Không có kết quả nào phù hợp, vui lòng nhập lại");
									}
								} while (checkTonTaiFirstName == 0);
								// làm sau khi check msv
								// Tìm kiếm và hiển thị thông tin SV theo Mã SV:
								sql = "select * from student where firstname like '%" + student.getFirstName() + "%'";
								rs = preperStatement.executeQuery(sql);
								String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
								System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
										"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
								while (rs.next()) {
									System.out.println(
											"-------------------------------------------------------------------------------------------------------------------------");
									format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
											rs.getString("FirstName"), rs.getString("Gender"), rs.getString("Classs"),
											rs.getFloat("MathsPoint"), rs.getFloat("PhysicalPoint"),
											rs.getFloat("ChemistryPoint"), rs.getFloat("averagePoint"));

								}
							}
						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 6: // 6
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							System.out.println(
									"------------------Hiển thị thông tin các sinh viên giỏi (ĐTB >= 8.0) theo thứ tự giảm dần điểm trung bình----------------");
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								sql = "select * from student where averagePoint >=8.0 order by averagePoint desc";
								rs = preperStatement.executeQuery(sql);
								if (!rs.isBeforeFirst() && rs.getRow() == 0) {
									System.out.println("Chưa có sinh viên nào phù hợp");
								} else {
									String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
											"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
									while (rs.next()) {
										System.out.println(
												"-------------------------------------------------------------------------------------------------------------------------");
										format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
										System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
												rs.getString("FirstName"), rs.getString("Gender"),
												rs.getString("Classs"), rs.getFloat("MathsPoint"),
												rs.getFloat("PhysicalPoint"), rs.getFloat("ChemistryPoint"),
												rs.getFloat("averagePoint"));

									}
								}
							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 7: // 7
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							System.out.println(
									"--------------Hiển thị thông tin các sinh viên khá (8.0 > ĐTB >= 6.5 ) theo thứ tự giảm dần điểm trung bình--------------");
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								sql = "select * from student where averagePoint >=6.5 and averagePoint <8.0 order by averagePoint desc";
								rs = preperStatement.executeQuery(sql);

								if (!rs.isBeforeFirst() && rs.getRow() == 0) {
									System.out.println("Chưa có sinh viên nào phù hợp");
								} else {
									String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
											"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
									while (rs.next()) {
										System.out.println(
												"-------------------------------------------------------------------------------------------------------------------------");
										format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
										System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
												rs.getString("FirstName"), rs.getString("Gender"),
												rs.getString("Classs"), rs.getFloat("MathsPoint"),
												rs.getFloat("PhysicalPoint"), rs.getFloat("ChemistryPoint"),
												rs.getFloat("averagePoint"));
									}
								}

							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 8: // 8
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							System.out.println(
									"------------Hiển thị thông tin các sinh viên Trung bình (6.5 > ĐTB >= 5 ) theo thứ tự giảm dần điểm trung bình-----------");
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								sql = "select * from student where averagePoint >=5.0 and averagePoint < 6.5 order by averagePoint desc";
								rs = preperStatement.executeQuery(sql);

								if (!rs.isBeforeFirst() && rs.getRow() == 0) {
									System.out.println("Chưa có sinh viên nào phù hợp");
								} else {
									String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
											"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
									while (rs.next()) {
										System.out.println(
												"-------------------------------------------------------------------------------------------------------------------------");
										format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
										System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
												rs.getString("FirstName"), rs.getString("Gender"),
												rs.getString("Classs"), rs.getFloat("MathsPoint"),
												rs.getFloat("PhysicalPoint"), rs.getFloat("ChemistryPoint"),
												rs.getFloat("averagePoint"));
									}
								}

							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 9: // 9
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							System.out.println(
									"-----------------Hiển thị thông tin các sinh viên Yếu (ĐTB < 5.0) theo thứ tự giảm dần điểm trung bình-------------------");
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								sql = "select * from student where averagePoint < 5.0 order by averagePoint desc";
								rs = preperStatement.executeQuery(sql);

								if (!rs.isBeforeFirst() && rs.getRow() == 0) {
									System.out.println("Chưa có sinh viên nào phù hợp");
								} else {
									String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
											"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
									while (rs.next()) {
										System.out.println(
												"-------------------------------------------------------------------------------------------------------------------------");
										format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
										System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
												rs.getString("FirstName"), rs.getString("Gender"),
												rs.getString("Classs"), rs.getFloat("MathsPoint"),
												rs.getFloat("PhysicalPoint"), rs.getFloat("ChemistryPoint"),
												rs.getFloat("averagePoint"));
									}
								}

							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 10: // 10
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								int idsv = 0;
								boolean checkidsv = true;
								Student student = new Student();
								String msv;
								do {
									System.out.println(
											"Nhập mã sinh viên cần Xoá (kiểu số nguyên dương, tối đa 4 chữ số):");
									System.out.print("MSV-");
									try {
										idsv = Integer.parseInt(sca.nextLine().trim());

										if (idsv > 0 && idsv <= 9) {
											msv = "MSV-00" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 9 && idsv <= 99) {
											msv = "MSV-0" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 99 && idsv <= 999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											checkidsv = true;
											System.out.println("Mã SV cần chỉnh sửa thông tin là: " + student.getMSV());
										} else if (idsv > 999 && idsv <= 9999) {
											msv = "MSV-" + idsv;
											student.setMSV(msv);
											System.out.println("Mã SV bạn vừa nhập là: " + student.getMSV());
											checkidsv = true;
										} else {
											System.err.println("Bạn nhập chưa đúng định dạng Mã SV");
											checkidsv = false;
										}
										// Kiểm tra msv k tồn tại
										sql = "select * from student where msv = '" + student.getMSV() + "' ; ";
										rs = preperStatement.executeQuery(sql);
										if (!rs.next()) {
											System.err.println("Mã sinh viên không tồn tại, mời bạn nhập lại");
											// conn.close();
											checkidsv = false;
										}
									} catch (Exception e) {
										System.err.println("Bạn chưa nhập đúng định dạng Mã SV");
										checkidsv = false;
									}
								} while (!checkidsv);
								// làm sau khi check msv
								// Tìm kiếm và hiển thị thông tin SV theo Mã SV:
								String sqlDelete = "delete from studentmanagement.student where msv = '"
										+ student.getMSV() + "';";
								int countRow = preperStatement.executeUpdate(sqlDelete);
								if (countRow > 0) {
									System.out.println("Xoá thành công ");
								}
							}
						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 11: // 11
				tieptuc = true; // kiểm tra có continue hay k
				do {
					try {
						conn = DriverManager.getConnection(connectionURL, userName, password);// mở
						if (conn != null) {
							System.out.println("connect success");
//					// check tồn tại bản ghi
							String sql = "select * from studentmanagement.student;";
							PreparedStatement preperStatement = null;
							preperStatement = conn.prepareStatement(sql);
							ResultSet rs = preperStatement.executeQuery(sql);
							System.out.println("------------------------Hiển thị thông tin toàn bộ sinh viên theo thứ tự Anpha-B của FirstName-----------------------");
							if (!rs.isBeforeFirst() && rs.getRow() == 0) {
								System.out.println(
										"Chưa có sinh viên nào trong danh sách. Vui lòng trở lại Menu, chọn chức năng 2 để nhập thông tin Sinh viên!");
							} else {// thực hiện
								sql = "select * from studentmanagement.student order by firstname asc;";
								rs = preperStatement.executeQuery(sql);

								if (!rs.isBeforeFirst() && rs.getRow() == 0) {
									System.out.println("Chưa có sinh viên nào phù hợp");
								} else {
									String format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
									System.out.format(format, "Mã SV", "LastName", "FirstName", "Giới tính", "Lớp",
											"Điểm Toán", "Điểm Lý", "Điểm Hóa", "Điểm TB");
									while (rs.next()) {
										System.out.println(
												"-------------------------------------------------------------------------------------------------------------------------");
										format = "|%1$-10s|%2$-15s|%3$-15s|%4$-12s|%5$-12s|%6$-12s|%7$-12s|%8$-12s|%9$-12s|\n";
										System.out.format(format, rs.getString("MSV"), rs.getString("LastName"),
												rs.getString("FirstName"), rs.getString("Gender"),
												rs.getString("Classs"), rs.getFloat("MathsPoint"),
												rs.getFloat("PhysicalPoint"), rs.getFloat("ChemistryPoint"),
												rs.getFloat("averagePoint"));
									}
								}

							}

						} else {
							System.out.println("k thành công");
						}
					} catch (SQLException e) {
						// TODO: handle exception
					} finally {
						conn.close();
					}
					boolean check = true;
					do {

						try {
							System.out.println("Nhập 'continue' để tiếp tục chức năng hoặc 'back' để quay lại menu!");
							String tuyChon = sca.nextLine().trim();
							if (!tuyChon.equalsIgnoreCase("continue") && !tuyChon.equalsIgnoreCase("back")) {
								System.err.println("Hãy chọn 1 trong 2 tuỳ chọn trên!");
								check = false;
							}
							if (tuyChon.equalsIgnoreCase("back")) {
								check = true;
								tieptuc = false;
								break;
							}
							if (tuyChon.equalsIgnoreCase("continue")) {
								check = true;
								System.out.println("Chức năng " + chonChucNang + " đã được khởi tạo lại!");
							}
						} catch (Exception e) {
							// TODO: handle exception
							check = false;
							System.err.println("Bạn nhập sai định dạng!");
						}
					} while (!check);
				} while (tieptuc);
				break;
			case 12: // 12
				System.out.println("Chương trình kết thúc! Bye ~ ~");
				System.exit(chonChucNang);
				break;
			default:
				System.out.println("Chức năng không tồn tại!");
				System.out.println("Vui lòng chọn lại chức năng!");
				break;
			}

		} while (chonChucNang != 12);

	}

	public static void hienthiMenu() {
		System.out.println("1: Hiển thị thông tin toàn bộ sinh viên");
		System.out.println("2: Nhập thông tin sinh viên");
		System.out.println("3: Chỉnh sửa thông tin sinh viên theo MSV");
		System.out.println("4: Tìm và hiển thị thông tin sinh viên theo mã sinh viên");
		System.out.println("5: Tìm và hiển thị thông tin sinh viên theo tên sinh viên");
		System.out.println("6: Hiển thị thông tin các sinh viên Giỏi theo thứ tự giảm dần điểm trung bình");
		System.out.println("7: Hiển thị thông tin các sinh viên Khá theo thứ tự giảm dần điểm trung bình");
		System.out.println("8: Hiển thị thông tin các sinh viên Trung Bình theo thứ tự giảm dần điểm trung bình");
		System.out.println("9: Hiển thị thông tin các sinh viên Yếu theo thứ tự giảm dần điểm trung bình");
		System.out.println("10: Xóa thông tin theo mã sinh viên ");
		System.out.println(
				"11: Hiển thị toàn bộ thông tin sinh viên theo tên sinh viên sắp xếp theo thứ tự chữ cái (A-Z)");
		System.out.println("12: Thoát!");
	}

	public static void kiemtraTiepTuc() {

	}
}
