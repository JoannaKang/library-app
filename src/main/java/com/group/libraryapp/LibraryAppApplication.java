package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 어노테이션 : 스프링부트 실행에 필요한 세팅을 자동으로 해줌?
@SpringBootApplication
public class LibraryAppApplication {

  public static void main(String[] args) {
    // 스프링 어플리케이션을 실행한다
    SpringApplication.run(LibraryAppApplication.class, args);
  }

}
