# :christmas_tree: Advent of Code (2020)

[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2020.svg)](https://github.com/TomPlum/advent-of-code-2020/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2020?color=brightgreen)
![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2020?color=informational)
![GitHub](https://img.shields.io/badge/instructions-99%25-success)
![GitHub](https://img.shields.io/badge/branches-93%25-orange)
![GitHub](https://img.shields.io/badge/stars-50%2F50-yellow)

## What is Advent of Code?

_Excerpt from the Advent of Code [website](https://adventofcode.com/2020/about);_

    "Advent of Code is an Advent calendar of small programming puzzles for a variety of skill sets
    and skill levels that can be solved in any programming language you like. People use them as a
    speed contest, interview prep, company training, university coursework, practice problems, or
    to challenge each other."
    
## About
After much deliberation I (anti-climatically) decided to take the same approach as 
[last year](https://github.com/TomPlum/advent-of-code-2019) and take my time with each puzzle by taking a more
enterprise-style test-driven approach in Kotlin as I really enjoyed it. I did, however, manage to complete both parts
every day (except Day 22 Part 2 - I was ill!) on the day of their release. This included writing a passing solution,
cleaning up somewhat, improving the performance, testing and documenting.
    
## Contents
* [Getting Started](#getting-started)
* [The Codebase](#the-codebase)
  * [Package Structure](#package-structure)
  * [Benchmarking](#solution-benchmarking)
  * [Static Code Analysis & Linting](#static-code-analysis--linting)
  * [JUnit5 & AssertK](#junit5--assertk)
  * [Test-Driven Development](#test-driven-development)
* [The Days](#the-days)
  * [The Most Fun](#the-most-fun)
  * [The Most Interesting](#the-most-interesting)
  * [The Most Challenging](#the-most-challenging)
* [Answer Table](#answer-table)
* [Advent Calendar](#advent-calendar)

## Getting Started
Simply clone or download the repository into your local environment and import it as a Gradle project in your IDE.
Running [Solutions.kt](https://git.io/JII6v) will run the parts from all the completed days, reporting all the
answers and runtimes in the console.

### Gradle Tasks
| Task               | Description                                                                                       |
|--------------------|---------------------------------------------------------------------------------------------------|
| `test`             | Runs the unit tests with coverage for the `implementation`, `solutions` and `common` sub-projects.|
| `detekt` 	         | Runs DeteKT with the custom configuration rules defined in detekt-config.yml.                     |

### IntelliJ Run Configurations
The `.run` directory contains XML configuration files from IntelliJ. Included are configurations for running the unit
tests in the `common`, `implementation` and `solutions` Gradle sub-projects as well as for each specific day.

## The Codebase
### Package Structure
The package structure was something that changed almost every day. My goal was "package-by-feature". For the first few 
days it seemed like I'd just end up having 25 different packages whose names were relevant keywords from that day. 
However, towards the latter half of the days, there were consistencies in the naming that started to make the language 
a little more ubiquitous. This allowed me to start grouping packages together and start abstracting out common code.

I created two Gradle root projects, `implementation` and `solutions`. With the former having sub-projects, `common`, for
behaviour that is commonly used across multiple days and `test-support` for unit test utility classes.

    -implementation
        -src
        -common
        -test-support
    -solutions

### Solution Benchmarking
This year I decided to take a different approach and write my own custom solution and benchmarking infrastructure.

I started by writing a simple `Solution` interface that accepts two formal generic-type parameters for the return
types of `part1()` and `part2()` respectively. I originally set the default implementation to throw an exception, but
later changed this to return null once I implemented the benchmarker.

I then wrote a `SolutionRunner` that accepts a `vararg` array of Solutions and runs both parts for all the provided
solutions. Finally, to make it runnable, I created `Solutions.kt` which simply contains a `main()` function and invokes
`SolutionRunner.run()`.

When I reached the days whose problems were orientated around runtime-complexity, I integrated Kotlin's 
[`measureNanoTime`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.system/measure-nano-time.html) into my
`SolutionRunner` and wrapped the `part1()` and `part2()` invocations in it. To store and display my results, I
integrated [Jackson Data Format XML](https://git.io/JLSlw) to easily serialise and de-serialise the results and then
created some reporting classes to format them and push them through my `AdventLogger`. I also exposed a JVM argument
that can be injected to change the verbosity of the report. The flag is `report` and can have the values `verbose` or
`compact`. E.g. `-Dreport=verbose`.

### Static Code Analysis & Linting
I used the [DeteKT](https://detekt.github.io/detekt/index.html) Gradle plugin to perform static code analysis on the
codebase. It produces a report containing all the code-smells that it found based on the set configuration.
The custom configuration is defined in `detekt-config.yml` and can be found in the `implementation` resources.

### JUnit5 & AssertK
The JUnit5 Jupiter API exposes some really nice functionality in the form of annotations and offers callback
interfaces for each of the life-cycle events. Utilising the `@ParameterizedTest` can significantly reduce the
quantity of test-methods in your unit tests suites while `@Nested` allows you to organise your tests in nested
classes.

[AssertK](https://git.io/JJd1g) is an assertion library inspired by [AssertJ](https://git.io/JJd1a) but for Kotlin.
I'm familiar with AssertJ and prefer the naming conventions and variety of assertions over the default JUnit offering.

### Test-Driven Development
I'm a huge advocate of testing in general, but particularly test-driven development. Some days were easy to test-drive
as they provided lots of examples that generally covered all branches of the algorithm and sometimes even edge cases.
Given I was taking a proper object-orientated approach, and not just hacking it into a single function, I still had to
write more tests than just the example inputs, but they were a nice baseline to run against while writing a solution.

## The Days
### The Most Fun

### The Most Interesting
[Day 15 - Rambunctious Recitation](docs/DAY15.MD) was an interesting day about an Elven memory game which was a facade
for a mathematical sequence called the ['Van Eck'](https://oeis.org/A181391) sequence. The sequence starts with `0` and
then each term considers the last. If it was the first occurrence of that terms value, then the next term is `0`. If
not, then the next term is equal to the number of terms-ago that last term occurred. For example, the first 30 terms are;

  `0, 0, 1, 0, 2, 0, 2, 2, 1, 6, 0, 5, 0, 2, 6, 5, 4, 0, 5, 3, 0, 3, 2, 9, 0, 4, 9, 3, 6, 14`

There's a [great video](https://www.youtube.com/watch?v=etMJxB-igrc) by Numberphile that explains the sequence. The most
interesting (and contextually relevant) attribute of the sequence is that it is not cyclical. This means that there is
no fancy math solution. Our algorithm must be exhaustive. This day was a test of data-structures. Part 1 was to simply
find the `2020th` number spoken in the game, no big deal. Part 2, however, was to find the `30,000,000th` number.

My initial implementation was slow. I got the solution in about `30s`. This involved several maps tracking the turns
each number last spoken on, the number of times it had been asked and a `LinkedList` of all the numbers. 

I then refactored one of the maps' value to an `IntArray` as opposed to a `List` which improved the solution runtime 
to about `20s`.

On the next pass, I realised we didn't need to store as much data as we were currently doing. I refactored to use only
a single map of the term value against the turn it was last spoken on, improving runtime to about `5s`.

Finally, I refactored the lone map into a primitive `IntArray` where the index was the term value, and the value was
the last turn it was spoken on. Less overhead and contiguous memory allocation improved the performance to `~600ms`.

And that was about as far as I could go. Due to the exhaustive nature of the algorithm, we _have_ to perform 30 million
iterations of our algorithm one way or another. I think at this point its limited by the language its written in and
maybe even the hardware.

### The Most Challenging

## Answer Table

| Day | Part 1 Answer  | Avg Time | Part 2 Answer     | Avg Time | Documentation                            |
|-----|----------------|----------|-------------------|----------|------------------------------------------|
| 01  | 802011         | 12ms     | 248607374         | 58ms     | [Report Repair](docs/DAY1.MD)            |
| 02  | 660            | 21ms     | 530               | 7ms      | [Password Philosophy](docs/DAY2.MD)      |
| 03  | 169            | 3ms      | 7560370818        | 2ms      | [Toboggan Trajectory](docs/DAY3.MD)      |
| 04  | 192            | 25ms     | 101               | 8ms      | [Passport Processing](docs/DAY4.MD)      |
| 05  | 904            | 8ms      | 669               | 4ms      | [Binary Boarding](docs/DAY5.MD)          |
| 06  | 6911           | 9ms      | 3473              | 24ms     | [Custom Customs](docs/DAY6.MD)           |
| 07  | 300            | 41ms     | 8030              | 14ms     | [Handy Haversacks](docs/DAY7.MD)         |
| 08  | 1832           | 9ms      | 662               | 24ms     | [Handheld Halting](docs/DAY8.MD)         |
| 09  | 20874512       | 149ms    | 3012420           | 60ms     | [Encoding Error](docs/DAY9.MD)           |
| 10  | 2176           | 1ms      | 18512297918464    | 1ms      | [Adapter Array](docs/DAY10.MD)           |
| 11  | 2427           | 2s 869ms | 2199              | 2s 504ms | [Seating System](docs/DAY11.MD)          |
| 12  | 319            | 2ms      | 50157             | 605μs    | [Rain Risk](docs/DAY12.MD)               |
| 13  | 102            | 654μs    | 327300950120029   | 71μs     | [Shuttle Search](docs/DAY13.MD)          |
| 14  | 9967721333886  | 6ms      | 4355897790573     | 239ms    | [Docking Data](docs/DAY14.MD)            |
| 15  | 1259           | 1ms      | 689               | 687ms    | [Rambunctious Recitation](docs/DAY15.MD) |
| 16  | 28882          | 3ms      | 1429779530273     | 11ms     | [Ticket Translation](docs/DAY16.MD)      |
| 17  | 269            | 146ms    | 848               | 2s 958ms | [Conway Cubes](docs/DAY17.MD)            |
| 18  | 11297104473091 | 19ms     | 185348874183674   | 15ms     | [Operation Order](docs/DAY18.MD)         |
| 19  | 171            | 78ms     | 369               | 201ms    | [Monster Messages](docs/DAY19.MD)        |
| 20  | 20899048083289 | 101ms    | 2476              | 1s 177ms | [Jurassic Jigsaw](docs/DAY20.MD)         |
| 21  | 2315           | 26ms     | cf,h,t,b,l,cb,cm,d| 2ms      | [Allergen Assessment](docs/DAY21.MD)     |
| 22  | 306            | 6ms      | 291               | 1s 299ms | [Crab Combat](docs/DAY22.MD)             |
| 23  | 58427369       | 1ms      | 111057672960      | 1s 22ms  | [Crab Cups](docs/DAY23.MD)               |
| 24  | 326            | 878μs    | 3979              | 3s 264ms | [Lobby Layout](docs/DAY24.MD)            |
| 25  | 19774660       | 104ms    | 49 Stars          | 15μs     | [Combo Breaker](docs/DAY25.MD)           |

Average Execution Time: 646ms \
Total Execution Time: 16s 168ms

## Advent Calendar
[![Calendar](https://i.gyazo.com/35e15bbee4d35f25457e6ac969b17937.gif)](https://gyazo.com/35e15bbee4d35f25457e6ac969b17937)