# :christmas_tree: Advent of Code (2020)

[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2020.svg)](https://github.com/TomPlum/advent-of-code-2020/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2020?color=brightgreen)
![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2020?color=informational)
![GitHub](https://img.shields.io/badge/instructions-99%25-success)
![GitHub](https://img.shields.io/badge/branches-93%25-orange)

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
* [Answer Table](#answer-table)

## Getting Started
Simply clone or download the repository into your local environment and import it as a Gradle project in your IDE.
Running [Solutions.kt](https://git.io/JII6v) will run the parts from all the completed days, reporting all the
answers and runtimes in the console.

### Gradle Tasks
| Task               | Description                                                                                       |
|--------------------|---------------------------------------------------------------------------------------------------|
| `test`             | Runs the unit tests with coverage for the `implementation`, `solutions` and `common` sub-projects.|

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

## Answer Table

| Day 	| Part 1 	| Part 2 	        | Name                                      | Documentation          |
|-------|-----------|-------------------|-------------------------------------------|------------------------|
| 01   	| 802011  	| 248607374         | Report Repair                             | [Link](docs/DAY1.MD)   |
| 02   	| 660       | 530               | Password Philosophy                       | [Link](docs/DAY2.MD)   |
| 03   	| 169       | 7560370818        | Toboggan Trajectory                       |                        |
| 04   	| 192       | 101               | Passport Processing                       |                        |
| 05   	| 904       | -                 | Binary Boarding                           |                        |
| 06   	| -         | -                 |                                           |                        |
| 07   	| -         | -                 |                                           |                        |
| 08   	| -         | -                 |                                           |                        |
| 09   	| -         | -                 |                                           |                        |
| 10   	| -         | -                 |                                           |                        |
| 11   	| -         | -                 |                                           |                        |
| 12   	| -         | -                 |                                           |                        |
| 13   	| -         | -                 |                                           |                        |
| 14   	| -         | -                 |                                           |                        |
| 15   	| -         | -                 |                                           |                        |
| 16   	| -         | -                 |                                           |                        |
| 17   	| -         | -                 |                                           |                        |
| 18   	| -         | -                 |                                           |                        |
| 19   	| -         | -                 |                                           |                        |
| 20   	| -         | -                 |                                           |                        |
| 21   	| -         | -                 |                                           |                        |
| 22   	| -         | -                 |                                           |                        |
| 23   	| -         | -                 |                                           |                        |
| 24   	| -         | -                 |                                           |                        |
| 25   	| -         | -                 |                                           |                        |