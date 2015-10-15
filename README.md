# Adventures in OODBMS

*brought to you by tyler and eddie*

## Many moons ago...

People liked object-oriented programming. And they liked databases. So they decided to put them both together. It wasn't nearly as magical as they would've liked.

The end.

## Plan

- Use db4o
- Database based on fog
- Draw some UML
- ?????
- Profit

## Usage

```sql
select g.name
from Games g
where g.developer.name = 'Power Cosmic';

max(select c.attack
    from Cards c
    where c.owner.username = "egurnee");

select g.name
from Games g
where count(g.genres) > 2);

select g.title, g.discription
from Games g;

select h.player.username
from h in PlayHistories
where h.game.developer = 'Power Cosmic';

select p.poster.username
from p in ( select f.posts
            from f in ForumThreads
            where f.title = 'Start Here');

select ph.player.username
from ph in PlayHistories
where ph.name.title = 'Fonkey Mong';

```

## License

This is all published under the MIT license.
