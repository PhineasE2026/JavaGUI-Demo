# Java GUI Demo
GUI demonstration with `Java Swing` for a **Choose-Your-Own Adventure** project.

### Naming Conventions
This structured naming approach helps keep states and scenarios organized and makes it easier to *add new branches* in the future. Each additional branch can incrementally follow this `sX_state` and `sX_scenario` format.

**State Naming**: Each unique `state` has a name that includes a prefix `s` followed by a `number` indicating its progression point in the story. For example:

* `start`: Beginning of the game.
* `s1_tavern`: Represents the state where the user reaches Tavern on the Green.
* `s2_castle`: A state that could be reached later in the story (e.g., upon exploring Belvedere Castle).

**Scenario Naming**: Each `scenario` has an identifier starting with s followed by a number to match the state progression, along with a descriptive suffix:

* `s1_none`: Initial scenario at Tavern on the Green without making any Yes/No choice.
* `s1_noWedding`: Scenario after the user selects "No" at Tavern on the Green.


#### RESOURCES:
* [How to Use Java Swing with GitHub Codespaces](https://apps.mvhs.io/resources/codespaces/)
* [Java Swing Documentation](https://www.javatpoint.com/java-swing)

