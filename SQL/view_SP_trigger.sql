alter view account_name as
select accountId, concat_ws(' ', b.first_name, b.last_name)  as  fullname
from accounts a
join users  b on a.accountId = b.account_id;

select * from account_name;

DELIMITER $$
create procedure withdraw( fromAccountId int, withdrawAmount dec(10,2) )
begin 
	if withdrawAmount <= 0 then
		signal sqlstate '45000'
        set message_text = 'số tiền rút phải lớn hơn 0';
	end if;
    
    update accounts 
    set amount = amount - withdrawAmount
    where accountId = fromAccountId;
end$$

DELIMITER ;

call withdraw(1, 100);

select * from accounts  where accountId = 1

delimiter $$
create procedure checkWithdrawal(
	fromAccountId int, 
    withdrawAmount dec(10,2)
)
begin 
	declare  balance dec(10,2);
    declare withdrawableAmount dec(10,2);
    declare message varchar(255);
    
    select amount into balance
    from accounts
    where accountId = fromAccountId;
    
    set withdrawableAmount = balance - 20;
    
    if withdrawAmount > withdrawableAmount then
		set message = concat('số dư ko đủ, số tiền lớn nhất có thể rút dc là: ', withdrawableAmount);
        signal sqlstate '45000'
			set message_text = message;
	end if;
end  $$

delimiter ; 

delimiter $$
create trigger before_accounts_update
before update 
on accounts for  each row
begin 
	call checkWithdrawal (
		OLD.accountId,
        OLD.amount - NEW.amount
    );
end $$

delimiter ;

call withdraw(1, 70);
select * from accounts  where accountId = 1