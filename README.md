A event dispatcher  for publish subscribe systems.
Usecase:
1) Client can register with the set of predicates.
Events get published to this system. Each event can be thought of string columns called C1, C2, … CN. Each event can thus be passed around as an array of Strings (C1 being the 0th element in the array, C2 the 1st and so on).
There are M subscribers – S1, … SM
Each subscriber registers a predicate that specifies what subset of the events it’s interested in. Each predicate can contain:
Equality clause on columns, for example: (C1 == “US”)
  Conjunctions of such clauses, example: 
  (C1 == “IN”) && (C2 == “home.php”) 
  (C1 == “IN”) && (C2 == “search.php”) && (C3 == “nytimes.com”)

2) Design a method for getting the subscriber ids which are registered for a given event.
ex : public List<String> findMatchingIds(String[] event /* assume each event has N Strings */);

  
