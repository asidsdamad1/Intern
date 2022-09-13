select users.id, users.first_name, users.last_name, accounts.name as account_name, accounts.amount
from users 
inner join accounts 
on users.account_id = accounts.accountId;

select  accounts.name as account_name, accounts.amount,users.first_name, users.last_name
from users
right join accounts 
on users.account_id = accounts.accountId;

select  accounts.name as account_name, accounts.amount,users.first_name, users.last_name
from accounts
left join users 
on users.account_id = accounts.accountId;

select * from users 
where account_id in (select accountId from accounts where name like 'John%');

alter table accounts 
add index name_index(name);
alter table accounts 
drop index id_index;
select * from accounts where name like 'Jo%' ;


alter table sales 
drop index name_index;

alter table sales 
add index id_index(id);

explain select * FROM example.sales where item_type = 'Office Supplies';
