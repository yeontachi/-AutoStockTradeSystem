import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPriceData {

  /* private String filePath = "User_Data_Log.csv"; */
  private String csvFile = ".csv";

  public void CreateUserFile(int UserID, String name, String filePath, int initialPrice) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + csvFile))) {
      writer.write("UserID, name, InitialPrice");
      writer.newLine();
      writer.write(UserID + ", " + name + ", " + initialPrice);
      writer.newLine();

    } catch (IOException e) {
      System.err.println("초기 파일 생성 중 오류 발생");
    }
  }

  public void UpdateUserPrice(int UserID, int Price, String filePath) {
    List<String[]> rows = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath + csvFile))) {
      String line;

      while ((line = reader.readLine()) != null) {
        String[] row = line.split(", ");

        if (row[0].equals(Integer.toString(UserID))) {

          String[] updateRow = Arrays.copyOf(row, row.length + 1);
          updateRow[row.length] = Integer.toString(Price);
          rows.add(updateRow);
        } else {
          rows.add(row);
        }
      }

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + csvFile))) {
        for (String[] row : rows) {
          writer.write(String.join(", ", row));
          writer.newLine();
        }
      }
    } catch (IOException e) {
      System.err.println("User data update 중 파일 불러오기 실패");
    }
  }

  /*
   * public void UserTradLog(int UserID, String CompanyName, String filePath) {
   * List<String[]> rows = new ArrayList<>();
   * try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
   * String line;
   * boolean headerUpdate=false;
   * 
   * while ((line = reader.readLine()) != null) {
   * String[] row = line.split(", ");
   * 
   * if (!headerUpdate) {
   * 
   * }
   * }
   * }
   * }
   */

  public void ShowUserLog(int UserID, String name, String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath + csvFile))) {
      String line;
      System.out.println(name + " 사용자 주식 기록");

      while ((line = reader.readLine()) != null) {
        String[] colums = line.split(",");
        for (String column : colums) {
          System.out.printf("%-15s", column);
        }
        System.out.println();
      }
    } catch (IOException e) {
      System.err.println("사용자 데이터를 로드하지 못했습니다.");
    }
  }

}
