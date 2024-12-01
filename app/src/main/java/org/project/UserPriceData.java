package org.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPriceData {
  private String csvFile = ".csv";

  public void CreateUserFile(int UserID, String name, int initialPrice) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + csvFile))) {
      writer.write("UserID, name, Price");
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

  public void UserTradeLog(User user, String companyName, int amount, int companyPrice) {
    List<String[]> rows = new ArrayList<>();
    int targetColumnIndex = -1;

    try (BufferedReader reader = new BufferedReader(new FileReader(user.getName() + csvFile))) {
      String line;
      boolean isHeader = true;

      while ((line = reader.readLine()) != null) {
        String[] row = line.split(", ");
        if (isHeader) {
          // 헤더 처리
          List<String> header = new ArrayList<>(Arrays.asList(row));

          if (header.contains(companyName)) {
            targetColumnIndex = header.indexOf(companyName); // 해당 열의 인덱스
          } else {
            header.add(companyName); // 새 열 추가
            targetColumnIndex = header.size() - 1;
          }

          rows.add(header.toArray(new String[0])); // 업데이트된 헤더 저장
          isHeader = false;
        } else {
          // 데이터 행 처리
          List<String> rowData = new ArrayList<>(Arrays.asList(row));
          if (targetColumnIndex >= rowData.size()) {
            rowData.add(String.format("%.2f", amount)); // 새 열에 값 추가
          } else {
            rowData.set(targetColumnIndex, String.format("%.2f", amount)); // 기존 열 값 업데이트
          }

          rows.add(rowData.toArray(new String[0]));
        }
      }

      // 파일 다시 쓰기
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(user.getName() + csvFile))) {
        for (String[] row : rows) {
          writer.write(String.join(", ", row));
          writer.newLine();
        }
      }
    } catch (IOException e) {
      System.err.println("User의 거래내역 저장 중 오류 발생: ");
    }
  }

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
