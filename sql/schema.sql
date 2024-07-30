CREATE TABLE WORDCOUNT (
    word VARCHAR(255) NOT NULL,
    cnt INT,
    PRIMARY KEY (word)
);

select * from WORDCOUNT;

insert into WORDCOUNT values ('hello', 1);
insert into WORDCOUNT values ('bye', 2);

update WORDCOUNT
set cnt = cnt + 1,
    word = '코틀린'
where word = 'hello';

delete from WORDCOUNT
where word = 'bye';

