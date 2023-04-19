package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class User {
    // id 필드를 primary key 로 간주한다
    // primary key 는 autoincrement 되도록 매핑한다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name="name") // name varchar(20)
    private String name;
    private Integer age;

    protected User() {}

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다."))
                    ;
        }

        this.name = name;
        this.age =age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() { return id; }

    public void updateName(String name) {
        this.name = name;
    }
}
