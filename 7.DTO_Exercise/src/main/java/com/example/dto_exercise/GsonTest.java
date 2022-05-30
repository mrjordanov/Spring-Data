package com.example.dto_exercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.*;


public class GsonTest {

    private static String json= """
            {
              "userName": "mnogoqko",
              "password": "1234"       
            }
            """;

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        LoginData loginData = new LoginData("mnogoqko", "1234");

        String  result = gson.toJson(loginData);

       /* String path="C:\\Users\\1Marin1\\Desktop\\Spring Data\\7.DTO_Exercise\\src\\test.json";
      BufferedWriter bufferedWriter= new BufferedWriter(new PrintWriter(path));
            bufferedWriter.write(result);
            bufferedWriter.flush();*/
        System.out.println(result);

        LoginData loginData1 = gson.fromJson(json, LoginData.class);
        System.out.println(loginData1);

    }

    static class LoginData implements Serializable {

        @Expose
        private String userName;
        @Expose
        private String password;

        public LoginData(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public LoginData() {
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
