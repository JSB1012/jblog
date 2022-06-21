-- scheme
show tables;

desc user;

desc blog;

desc category;

desc post;

select * from user;

select * from blog;
select * from category;
select * from post;
insert into category values(null, '테스트2', '테스트한경우', 'jsb');
insert into post values(null, 'Spring2', 'spring어렵다2', 2);

			select 
				c.no, c.name, c.description, c.blog_id, count(p.no) as postCount
			from 
				category c, post p
			where 
				c.no = p.category_no
			and
				blog_id = 'jsb'
			order by
				c.no desc;
                
                select no,name,description,blog_id,
                from category c, post p
                where blog_id = 'jsb'
                order by no desc;
                
                select count(p.no)
                from post p, category c
                where p.category_no = c.no;
                
                