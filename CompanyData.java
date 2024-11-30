import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyData {

  private String filePath = "Company_Data_Log.csv";

  public void CreateCompanyFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("CompanyID, name, InitialPrice");
      writer.newLine();

    } catch (IOException e) {
      System.err.println("초기 파일 생성 중 오류 발생");
    }
  }

  public void AddInitialCompany(String CompanyID, String name, int initialPrice) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      writer.write(CompanyID + ", " + name + ", " + initialPrice);
      writer.newLine();
    } catch (IOException e) {
      System.err.println("회사 초기 데이터 쓰기 중 오류 발생");
    }
  }

  public void UpdateCompanyPrice(String CompanyID, int Price) {
    List<String[]> rows = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = reader.readLine()) != null) {
        String[] row = line.split(", ");

        if (row[0].equals(CompanyID)) {
          String[] updateRow = Arrays.copyOf(row, row.length + 1);
          updateRow[row.length] = Integer.toString(Price);
          rows.add(updateRow);
        } else {
          rows.add(row);
        }
      }
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (String[] row : rows) {
          writer.write(String.join(", ", row));
          writer.newLine();
        }
      }
    } catch (IOException e) {
      System.err.println("company data update 중 파일 불러오기 실패");
    }
  }

  public void ShowCompanyLog() {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      System.out.println("회사 주가 기록");

      while ((line = reader.readLine()) != null) {
        String[] colums = line.split(",");
        for (String column : colums) {
          System.out.printf("%-15s", column);
          // System.out.printf("|");
        }
        System.out.println();
      }
    } catch (IOException e) {
      System.err.println("회사 주가 데이터를 로드하지 못했습니다.");
    }
  }

}
