# Process Synchronizaion

## 데이터의 접근

<img width="610" alt="스크린샷 2023-02-07 오후 9 47 11" src="https://user-images.githubusercontent.com/71378447/217265039-b3e5c07e-5cb8-4d1d-a798-3672af619017.png">


## Race Condition


<img width="611" alt="스크린샷 2023-02-07 오후 9 51 24" src="https://user-images.githubusercontent.com/71378447/217265123-6d7a6a6a-5a87-4558-846b-efbd997b29f8.png">



## OS에서의 Race Condition

### OS 에서 race condition은 언제 발생하는가?

#### 1. kernel 수행 중 인터럽트 발생 시

<img width="607" alt="스크린샷 2023-02-07 오후 10 33 03" src="https://user-images.githubusercontent.com/71378447/217265163-5fcf3274-c5db-460e-8e8a-0b9f3bae1bf8.png">

#### 2. Process가 system call을 하여 kernel mode로 수행 중인데, context switch가 일어나는 경우

<img width="609" alt="스크린샷 2023-02-07 오후 10 35 08" src="https://user-images.githubusercontent.com/71378447/217265200-dcc20f1b-5de9-45d8-a395-92306476e729.png">


#### 3. Multiprocessor에서 shared memory 내의 kernel data

<img width="608" alt="스크린샷 2023-02-07 오후 10 38 13" src="https://user-images.githubusercontent.com/71378447/217265236-cc8eaff7-0d74-4811-9d54-b48af6e35b8c.png">



&nbsp;


## Process Synchronization 문제

- 공유 데이터의(shared data)의 동시 접근(concurrent access)은 데이터의 불일치 문제(inconsistency)를 발생시킬 수 있다.

- 일관성(consistency) 유지를 위해서는 협력 프로세스(cooperating process)간의 실행 순서(orderly execution)를 정해주는 메커니즘 필요

- **Race Condtion** 
  - 여러 프로세스들이 동시에 공유 데이터를 접근하는 상황
  - 데이터의 최종 연산 결과는 마지막에 그 데이터를 다룬 프로세스에 따라 달라짐
  
- race condition을 막기 위해서는 concurrent process는 동기화(synchronize)되어야 한다. 


## Example of a Race Condition

<img width="608" alt="스크린샷 2023-02-07 오후 10 44 54" src="https://user-images.githubusercontent.com/71378447/217265249-34424dc1-941d-415b-b3b0-e40dcab6281d.png">


## The Critical-Section Problem

- n개의 프로세스가 공유 데이터를 동시에 사용하기를 원하는 경우

- 각 프로세스의 code segment에는 공유 데이터를 접근하는 코드인 critical section이 존재

- Problem
  - 하나의 프로세스가 critical section에 있을 때 다른 모든 프로세스는 critical section에 들어갈 수 없어야 한다.
  
  
