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
enterprise-style test-driven approach in Kotlin as I really enjoyed it.
    
## Contents
* [Getting Started](#getting-started)
* [The Codebase](#the-codebase)
  * [Package Structure](#package-structure)
  * [Benchmarking](#solution-benchmarking)
  * [Static Code Analysis & Linting](#static-code-analysis--linting)
  * [JUnit5 & AssertK](#junit5--assertk)
  * [Test-Driven Development](#test-driven-development)
* [Answer Table](#answer-table)

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

#### JUnit5 & AssertK
The JUnit5 Jupiter API exposes some really nice functionality in the form of annotations and offers callback
interfaces for each of the life-cycle events. Utilising the `@ParameterizedTest` can significantly reduce the
quantity of test-methods in your unit tests suites while `@Nested` allows you to organise your tests in nested
classes.

[AssertK](https://git.io/JJd1g) is an assertion library inspired by [AssertJ](https://git.io/JJd1a) but for Kotlin.
I'm familiar with AssertJ and prefer the naming conventions and variety of assertions over the default JUnit offering.

#### Test-Driven Development
I'm a huge advocate of testing in general, but particularly test-driven development. Some days were easy to test-drive
as they provided lots of examples that generally covered all branches of the algorithm and sometimes even edge cases.
Given I was taking a proper object-orientated approach, and not just hacking it into a single function, I still had to
write more tests than just the example inputs, but they were a nice baseline to run against while writing a solution.

## Answer Table

| Day | Part 1 Answer  | Avg Time | Part 2 Answer     | Avg Time | Documentation                            |
|-----|----------------|----------|-------------------|----------|------------------------------------------|
| 01  | 802011         | 12ms     | 248607374         | 58ms     | [Report Repair](docs/DAY1.MD)            |
| 02  | 660            | 21ms     | 530               | 7ms      | [Password Philosophy](docs/DAY2.MD)      |
| 03  | 169            | 3ms      | 7560370818        | 2ms      | [Toboggan Trajectory](docs/DAY3.MD)      |
| 04  | 192            | 25ms     | 101               | 8ms      | [Passport Processing](docs/DAY4.MD)      |
| 05  | 904            | 8ms      | 669               | 4ms      | [Binary Boarding](docs/DAY5.MD)          |
| 06  | 6911           | 9ms      | 3473              | 24ms     | [Custom Customs](docs/DAY6.MD)           |
| 07  | 300            | 107ms    | 8030              | 68ms     | [Handy Haversacks](docs/DAY7.MD)         |
| 08  | 1832           | 9ms      | 662               | 24ms     | [Handheld Halting](docs/DAY8.MD)         |
| 09  | 20874512       | 149ms    | 3012420           | 60ms     | [Encoding Error](docs/DAY9.MD)           |
| 10  | 2176           | 1ms      | 18512297918464    | 1ms      | [Adapter Array](docs/DAY10.MD)           |
| 11  | 2427           | 2s 869ms | 2199              | 2s 504ms | [Seating System](docs/DAY11.MD)          |
| 12  | 319            | 2ms      | 50157             | 605μs    | [Rain Risk](docs/DAY12.MD)               |
| 13  | 102            | 654μs    | 327300950120029   | 71μs     | [Shuttle Search](docs/DAY13.MD)          |
| 14  | 9967721333886  | 6ms      | 4355897790573     | 239ms    | [Docking Data](docs/DAY14.MD)            |
| 15  | 1259           | 1ms      | 689               | 687ms    | [Rambunctious Recitation](docs/DAY15.MD) |
| 16  | 28882          | 3ms      | 1429779530273     | 11ms     | [Ticket Translation](docs/DAY16.MD)      |
| 17  | 269            | 213ms    | 848               | 3s 65ms  | [Conway Cubes](docs/DAY17.MD)            |
| 18  | 11297104473091 | 19ms     | 185348874183674   | 15ms     | [Operation Order](docs/DAY18.MD)         |
| 19  | 171            | 78ms     | 369               | 201ms    | [Monster Messages](docs/DAY19.MD)        |
| 20  | 20899048083289 | 101ms    | 2476              | 1s 177ms | [Jurassic Jigsaw](docs/DAY20.MD)         |
| 21  | 2315           | 26ms     | cf,h,t,b,l,cb,cm,d| 2ms      | [Allergen Assessment](docs/DAY21.MD)     |
| 22  | 306            | 6ms      | 291               | 1s 299ms | [Crab Combat](docs/DAY22.MD)             |
| 23  | 58427369       | 1ms      | 111057672960      | 1s 22ms  | [Crab Cups](docs/DAY23.MD)               |
| 24  | 326            | 878μs    | 3979              | 3s 264ms | [Lobby Layout](docs/DAY24.MD)            |
| 25  | 19774660       | 104ms    | 49 Stars          | 15μs     | [Combo Breaker](docs/DAY25.MD)           |

Average Execution Time: 662ms \
Total Execution Time: 16s 563ms