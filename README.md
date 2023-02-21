# Pokemon Adventure
## Description
---
In this pokemon universe there are pokemons and pokemon trainers.

Every trainer has the following attributes: ***Name***, ***Age*** and his ***Pokemons***(all the pokemons he owns).

Every pokemon has the following attributes: ***Name***, ***HP***, ***Attack***(the amount of damage a normal attack inflicts), ***Special Attack***(the amount of damage a special attack inflicts), ***Defense***(the amount of damage blocked after receiving a normal attack), ***Special Defense***(the amount of damage blocked after receiving a special attack).
Also,
each pokemon will have two special abilities that produce a hit ("damage")
and/or immobilize the pokemon they are fighting ("stun") and/or dodge the next one movement of the enemy ("dodge") and a movement recharge time ("cooldown").

A pokemon will have only one type of attack - either a normal attack or a special attack.

This universe is a bit different than the usual Pokemon and offers the possibility of a pokemon to "carry" with him in a battle up to 3 objects that improve his qualities. Trainers have access to a lot of items and can give them to pokemon before a battle. Not there is a limitation on a single item being able to be used by only one pokemon of one trainer. That is, if we have the "shield" object and a trainer with 3 pokemon, all pokemon can carry the shield. The only limitation is that a pokemon cannot carry the same item more than once or in the same battle.

## Adventure
---

Our adventure takes place in an arena. Two coaches will enter the arena each time, each of them being able to choose which pokemon from their list to enter the arena with and what objects to give him.

In this arena, 3 events decided randomly by the arena can happen:
1. Each trainer along with their pokemon will battle a neutral pokemon separately
of type 1 (Neutral1).
2. Each trainer along with their pokemon will battle a neutral pokemon separately
of type 2 (Neutral2).
3. Trainers along with their pokemon will duel each other. (This will be the last battle
from the arena and the trainer who wins this battle will be declared the winner of the adventure).

## Battle
---

A battle will play out like this:
* At each point in time each pokemon in battle will execute a command
indicated by the coach.
* The trainer is the one who controls the pokemon and gives it commands. Orders can be:
    - Normal attack - then the pokemon will attack the opposing pokemon with attack type on which he possesses (normal / special).
    - Skill 1
    - Skill 2
* If a skill is executed it will return 4 pieces of information:
    - Damage produced
    - If it is stun
    - If they dodge with this skill
    - Cooldown, how many moments of time it takes until the ability will be again disposable
* When a pokemon is attacked it will update its status (HP and if it is
blocked, i.e. if he got stun) in accordance with the enemy's movement and his movement:
    - If he performed an ability that contains dodge then his status remains
unchanged no matter what the other pokemon did. Practically, in this case no
it matters what the opposing pokemon did and the details below can be ignored.
    - If the enemy performed a normal attack then he will lose HP equal to
the difference between the enemy's attack type and its defense against that attack type.
Example: if the enemy pokemon has normal attack 50 and the current pokemon has
defense 20, he will lose 30 health; if the enemy pokemon has attack
special 25 and the current pokemon has special defense 5, it will lose 20 points
of life.
    - If the enemy has executed an ability then the damage done by the ability will deducts health points regardless of the current pokemon's defense. If the enemy's ability is an ability that contains stun then the pokemon won't perform any move at the next time point.
* The adventure will end when one or both pokemon run out of health.
This can happen when pokemon are fighting neutral pokemon or when
coaches fight each other. Arena will have to return the name of the winning trainer or
"Draw" in case of a tie.
* After a battle (event) is finished, pokemons return to their original state from the point
of HP's view. Also, the pokemon of a trainer who won a battle
(regardless of battle type (vs a neutral pokemon or vs another trainer's pokemon))
he will get 1 extra point to all his characteristics: hp, attack (normal or special),
defense and special defense.

Note: The battle between the two trainers will be done using threads.

## Pokemons
---

| Name | HP | Normal Attack | Special Attack | Def | Special Def | Ability 1 | Ability 2 |
| ---- | -- | ------------- | -------------- | --- | ----------- | --------- | --------- |
| Neutrel1 | 10 | 3 | N/A | 1 | 1 | N/A | N/A |
| Neutrel2 | 20 | 4 | N/A | 1 | 1 | N/A | N/A |
| Pikachu | 35 | N/A | 4 | 2 | 3 | Dmg: 6<br>Stun: No<br>Dodge: No<br>Cd: 4 | Dmg: 4<br>Stun: Yes<br>Dodge: Yes<br>Cd: 5 |
| Bulbasaur | 42 | N/A | 5 | 3 | 1 | Dmg: 6<br>Stun: No<br>Dodge: No<br>Cd: 4 | Dmg: 5<br>Stun: No<br>Dodge: No<br>Cd: 3 |
| Charmander | 50 | 4 | N/A | 3 | 2 | Dmg: 4<br>Stun: Yes<br>Dodge: No<br>Cd: 4 | Dmg: 7<br>Stun: No<br>Dodge: No<br>Cd: 6 |
| Squirtle | 60 | N/A | 3 | 5 | 5 | Dmg: 4<br>Stun: No<br>Dodge: No<br>Cd: 3 | Dmg: 2<br>Stun: Yes<br>Dodge: No<br>Cd: 2 |
| Snorlax | 62 | 3 | N/A | 6 | 4 | Dmg: 4<br>Stun: Yes<br>Dodge: No<br>Cd: 5 | Dmg: 0<br>Stun: No<br>Dodge: Yes<br>Cd: 5 |
| Vulpix | 36 | 5 | N/A | 2 | 4 | Dmg: 8<br>Stun: Yes<br>Dodge: No<br>Cd: 6 | Dmg: 2<br>Stun: No<br>Dodge: Yes<br>Cd: 7 |
| Eevee | 39 | N/A | 4 | 3 | 3 | Dmg: 5<br>Stun: No<br>Dodge: No<br>Cd: 3 | Dmg: 3<br>Stun: Yes<br>Dodge: No<br>Cd: 3 |
| Jigglypuff | 34 | 4 | N/A | 2 | 3 | Dmg: 4<br>Stun: Yes<br>Dodge: No<br>Cd: 4 | Dmg: 3<br>Stun: Yes<br>Dodge: No<br>Cd: 4 |
| Meowth | 41 | 3 | N/A | 4 | 2 | Dmg: 5<br>Stun: No<br>Dodge: Yes<br>Cd: 4 | Dmg: 1<br>Stun: No<br>Dodge: Yes<br>Cd: 3 |
| Psyduck | 43 | 3 | N/A | 3 | 3 | Dmg: 2<br>Stun: No<br>Dodge: No<br>Cd: 4 | Dmg: 2<br>Stun: Yes<br>Dodge: No<br>Cd: 5 |

## Items
---

| Name | HP | Attack | Special Attack | Def | Special Defefense |
| ---- | -- | -----  | -------------- | --- | ----------------- |
| Scut | - | - | - | +2 | +2 |
| Vest | +10 | - | - | - | - |
| Sword | - | +3 | - | - | - |
| Magic Stick | - | - | +3 | - | - |
| Vitamins | +2 | +2 | +2 | - | - |
| Christmas Tree | - | +3 | - | +1 | - |
| Cape | - | - | - | - | +3 |

## Implementation details
---

First of all read the input file where we find the information about the trainers, their pokemons and items. 2 trainers are required to start an adventure(adventures are singleton java classes). Pokemons are created using java Factory design pattern, items are created using java Builder design pattern.

During the adventure there will be decisions between 3 possible events: ***battle with Neutrel1***, ***battle with Neutrel2*** or ***battle between trainers***(a group of battles). The battle types are randomly choosen. In the neutrel battles a random pokemon from each trainer will fight with the Neutrel. These 2 battles will run simultaneously using java ExecutorService.

Before the battle starts the pokemon will equip his items marking the transition from the peaceful state to the fighting state.


Each battle won will level up the winner pokemon adding +1 to all of his stats(except the attack type the pokemon don't have)

When a battle between trainers is decided there will run 3 simultaneous battles between each pokemon of each trainer(1st pokemon trainer 1 vs 1st pokemon trainer2...). After that a final battle will be held between 2 most powerful pokemons of each trainer(power is considered the sum of all pokemon's stats). The winner trainner is announced and the adventure ends.

## Design patterns
---

Singleton - there is a single instance of adventure, pokeFactory and itemBuilder.

Factory - used to create pokemon objects depending on his name; pokemons have almost all the stat fields completed with a specific value, so the factory design pattern was decided for creating pokemons

Builder - used to build items by their name; only the required item field is completed depending on item's name, the rest were by default completed with 0

State - comportamental design pattern used to mark the transition of the pokemon from peaceful state to fighting state and vice versa