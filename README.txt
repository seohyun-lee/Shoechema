# DataBase Team Project 이데아팀 코드 작동 방법
| 프로젝트명: Shoechema

1. SQL_Files/createschema.sql 와 SQL_Files/initdata.sql 파일을 실행해 database schema를 생성하고, 초기 데이터를 넣어준다.

2. JAVA_Files/util/DatabaseConnection.java 파일에서 {db명}, {유저명}, {비밀번호}를 개인 환경에 맞게 설정한다.
   ex) private static final String URL = "jdbc:mysql://localhost:3306/shoechema";
       private static final String USER = "root";
       private static final String PASSWORD = "1234";

3. JAVA_Files/GuestPage.java 파일을 실행해 시작한다.
