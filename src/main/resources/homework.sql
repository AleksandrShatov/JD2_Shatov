-- 1.
select name
from users
where date_part('year', age(birth_date)) > 18
  and date_part('year', age(birth_date)) < 25;

-- 2.
select concat_ws(' ', users.name, users.surname) as full_name, count(cars.owner) as cars_count
from users
         inner join cars on users.id = cars.owner
group by users.id;

-- 3. TODO - не работает требовалось по top3 для каждого диллера
select d.name dealer, count(c.model) model_count, c.model model
from dealer d
         inner join cars c on d.id = c.dealer_id
group by c.model, d.name
order by d.name, model_count desc
limit 3;

-- 4.
select login
from users
         inner join (select owner, count(owner) as count from cars group by owner order by count desc) as car_counters
                    on users.id = car_counters.owner
where count > 3;

-- 5.
select name, sum_prices
from dealer
         inner join (select dealer_id, sum(price) sum_prices from cars group by dealer_id) as d_p
                    on dealer.id = d_p.dealer_id;

-- 6.
select count(owner) as unique_users
from (
         select distinct owner
         from cars
         where price > (select avg(price) avg_price from cars)
     ) as owners;