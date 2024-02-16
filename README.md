On the roadmap:
1. Add more tests, like covering the limit/offset query params feature, edge cases such as bad csv file format and so on.
2. Fix the PlayersRepository (CrudRepository<Player, String>), which uses H2 DB that can be potentially persistent. The issue I have: data.sql does not insert the data if
the table name is the same as of the @Entity,@Table (Player) -- DONE
3. Resiliency regarding reading the CSV - big enough could cause OOM - so read it in batches and insert into persistent DB.
4. Check the limit query param is not too big (avoid OOM).
