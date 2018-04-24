DROP DATABASE xkgl;
create DATABASE xkgl DEFAULT CHARSET = utf8;
use xkgl;
    create table Course (
        id bigint not null AUTO_INCREMENT,
        accumulation integer not null,
        credit integer not null,
        hours integer not null,
        max integer not null,
        min integer not null,
        no varchar(255) not null Unique,
        status bit not null,
        time varchar(255) not null,
        title varchar(255) not null,
        courseType_id bigint not null,
        teacher_id bigint not null,
        primary key (id)
    );

    create table CourseType (
        id bigint not null AUTO_INCREMENT,
        description varchar(255) not null,
        no varchar(255) not null,
        primary key (id)
    );

    create table CourseSelection (
        id bigint not null auto_increment Unique,
        course_id bigint not null,
        student_id bigint not null,
        primary key (student_id,course_id)
    );

    create table EduAdmin (
        id bigint not null AUTO_INCREMENT,
        name varchar(255) not null,
        no varchar(255) not null Unique,
        password varchar(255) not null,
        sex varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table Notice (
        id bigint not null AUTO_INCREMENT,
        issueTime datetime not null,
        text varchar(255) not null,
        title varchar(255) not null,
        eduAdmin_id bigint not null,
        primary key (id)
    );

    create table OpenPeriod (
        id bigint not null AUTO_INCREMENT,
        endTime datetime not null,
        startTime datetime not null,
        primary key (id)
    );

    create table ProTitle (
        id bigint not null AUTO_INCREMENT,
        description varchar(255) not null,
        no varchar(255) not null,
        primary key (id)
    );

    create table Student (
        id bigint not null AUTO_INCREMENT,
        name varchar(255) not null,
        no varchar(255) not null Unique,
        password varchar(255) not null,
        sex varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table SysAdmin (
        id bigint not null AUTO_INCREMENT,
        name varchar(255) not null,
        no varchar(255) not null Unique,
        password varchar(255) not null,
        sex varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table Teacher (
        id bigint not null AUTO_INCREMENT,
        name varchar(255) not null,
        no varchar(255) not null Unique,
        password varchar(255) not null,
        sex varchar(255) not null,
        username varchar(255) not null,
        proTitle_id bigint not null,
        primary key (id)
    );

    alter table Course
        add constraint FK78A7CC3BD6F0F5DA
        foreign key (courseType_id)
        references CourseType(id);

    alter table Course
        add constraint FK78A7CC3BC648E65A
        foreign key (teacher_id)
        references Teacher(id);

    alter table CourseSelection
        add constraint FK37755791D006A3FA
        foreign key (course_id)
        references Course(id);

    alter table CourseSelection
        add constraint FK37755791B5AD9BBA
        foreign key (student_id)
        references Student(id);

    alter table Notice
        add constraint FK8B6C82F829783B1A
        foreign key (eduAdmin_id)
        references EduAdmin(id);

    alter table Teacher
        add constraint FKD6A63C28E184DDA
        foreign key (proTitle_id)
        references ProTitle(id);
