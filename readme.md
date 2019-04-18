## Overview
The system is placed as the Small model (prototype version) in development work of the project Service Management Platform System.
By creating the Small model version, we will confirm the implementation details in offshore development by Hikesiya (Japan) and ITS-J (Vietnam),
and then could carry out the project in a manner close to actual warfare and rehearse for the upcoming development that is objected.

In addition, this system development includes a series of flows, implementation contents of screen display - screen input - data registration / modification,
and verification of whether or not to be implemented according to implementation rules and framework.

## Environment setup
- Java SDK 11.x
- Tomcat 9.x
- SpringBoot 2.x
- Gradle 5.x
- MySql 8.x
- Your favorite IDE (Eclipse, IntelliJ, Visual Studio Code...)

## Database
- Create a database with name: sbps
- Execute the sql script from **docs** project at Gitlab
- Setting your username and password to *application.settings* both of **portal** and **businessLogic** modules

```
// MySQL username and password
spring.datasource.username=<username>
spring.datasource.password=<password>
```

- Open database name sbps in phpMyAdmin and execute query to insert data 
for table *m_common_codes*:
```
INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'FUNCTION_CODE', 'F001', 'お知らせ一覧', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'FUNCTION_CODE', 'F002', 'お知らせ詳細', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'FUNCTION_CODE', 'F003', 'お知らせ登録(ゆっくり)', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'FUNCTION_CODE', 'F004', 'テンプレート登録', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'OPERATION_CODE', 'OP001', '登録', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'OPERATION_CODE', 'OP002', '更新', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'OPERATION_CODE', 'OP003', '削除', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `m_common_codes` (`id`, `code_type`, `code`, `description`, `created_by`, `created_date`, `modified_by`, `modified_date`, `create_date`) VALUES (NULL, 'OPERATION_CODE', 'OP004', '検索', '#', CURRENT_TIMESTAMP, '#', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```


## How to run
- Open the terminal or cmd
- Run there commands

```
gradle build
gradle bootrun
```

The application will be run on http://localhost:8080/sbps




