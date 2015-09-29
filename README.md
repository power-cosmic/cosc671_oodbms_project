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
select g.name from Game g where g.getDeveloperName() == 'Power Cosmic';
select p from Post p where p.rootPost == null;
select count(g.name) from Game g where g.getRating() >= 4 group by name: g.getDeveloperName()



```

## License

This is all published under the MIT license.
