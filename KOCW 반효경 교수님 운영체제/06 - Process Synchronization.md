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
  
  
  
## Initial Attempts to Solve Problem

<img width="607" alt="스크린샷 2023-02-07 오후 11 02 22" src="https://user-images.githubusercontent.com/71378447/217300719-b451e104-0d61-4ca2-a656-4c45a989e448.png">


## 프로그램적 해결법의 충족 조건

- Mutual Exclusion (상호 배제)
  - 프로세스 Pi가 critical section 부분을 수행 중이면 다른 모든 프로세스들은 그들의 critical section에 들어가면 안된다.
  
- Progress (진행)
  - 아무도 critical section에 있지 않은 상태에서 critical section에 들어가고자 하는 프로세스가 있으면 critical section에 들어가게 해주어야 한다.
  
- Bounded Waiting (유한 대기)
  - 프로세스가 critical section에 들어가려고 요청한 후부터 그 요청이 허용될 때까지 다른 프로세스들이 critical section에 들어가는 횟수에 한계가 있어야 한다.
  
  
- 가정
  - 모든 프로세스의 수행 속도는 0보다 크다
  - 프로세스들 간의 상대적인 수행 속도는 가정하지 않는다.
  

### Algorithm1 

<img width="607" alt="스크린샷 2023-02-07 오후 11 11 16" src="https://user-images.githubusercontent.com/71378447/217300748-4c2b5192-f29d-4ceb-be4d-a3fcdaf70476.png">


### Algorithm2

<img width="608" alt="스크린샷 2023-02-07 오후 11 18 05" src="https://user-images.githubusercontent.com/71378447/217300790-e7457340-750b-4fdb-91e2-28277b4147f5.png">


### Algorithm3
(피터슨 알고리즘!!)

<img width="607" alt="스크린샷 2023-02-07 오후 11 54 12" src="https://user-images.githubusercontent.com/71378447/217300818-08241277-fcde-4c38-8184-957dbed73ba5.png">


## Synchronization Hardware

<img width="608" alt="스크린샷 2023-02-07 오후 11 59 20" src="https://user-images.githubusercontent.com/71378447/217300843-be2573e2-14e2-421a-b8c1-c732e4730ffd.png">


## Semaphores

<img width="609" alt="스크린샷 2023-02-08 오전 12 05 48" src="https://user-images.githubusercontent.com/71378447/217300965-79eabf0f-aae7-4ccd-a3b2-af93d70faf22.png">

## Critical Section of n Processes

>프로그래머가 일일이 critical section에 대한 내용을 하나하나 코드로 작성하는 것이 아니라 (ex 위의 Algorithm 들..) <p>
> **SemaPhore**라는 추상 자료형을 사용하여 더욱 쉽게 개발할 수 있게 함(아래 그림 참고) 


<img width="609" alt="스크린샷 2023-02-08 오전 12 38 37" src="https://user-images.githubusercontent.com/71378447/217301013-f55d6ad3-b235-497f-9f12-d8f698aec1f9.png">

## Block / Wakeup Implementation

<img width="606" alt="스크린샷 2023-02-08 오전 12 44 34" src="https://user-images.githubusercontent.com/71378447/217301408-00a1387e-0c51-4edb-9a38-aa4fb0fcdc8e.png">

  
<img width="598" alt="스크린샷 2023-02-08 오전 12 50 29" src="https://user-images.githubusercontent.com/71378447/217301439-1377af95-4f7d-4dce-b36e-8fcb7ce9ce8c.png">



## Two types of Semaphores

- Counting semaphore
  - 도메인이 0 이상인 임의의 정수 값
  
  - 주로 resource counting에 사용
  
- Binary semaphore (= mutex)
  - 0 또는 1 값만 가질 수 있는 semaphore
  
  - 주로 mutual exclusion (lock/unlock)에 사용
  

## Deadlock and Starvation

<img width="605" alt="스크린샷 2023-02-08 오전 12 57 32" src="https://user-images.githubusercontent.com/71378447/217301489-bc1d8a7d-f9a8-4620-bf97-4ac5f160f45f.png">


- 위의 사진에서 Deadlock을 해결하는 방법
  - 프로세스1과 프로세스 2가 같은 순서로 세마포어를 얻도록 하자 
  
  - 예를 들어 프로세스0과 1이 모두 P(S)를 먼저 하게 한다면 데드락이 발생하지 않게 된다.

  
## Classical Problems of Synchronization

### 1. Bounded-Buffer Problem(Producer-Consumer Problem). 생산자-소비자 문제

<img width="610" alt="스크린샷 2023-02-08 오전 1 23 52" src="https://user-images.githubusercontent.com/71378447/217323194-951ceb88-51c3-4d47-ab1f-7a4a8bcf2d32.png">

<img width="609" alt="스크린샷 2023-02-08 오전 1 36 24" src="https://user-images.githubusercontent.com/71378447/217323425-b7f6496f-f9bb-4482-ba54-1e5575596445.png">


### 2. Readers and Writers Problem

<img width="606" alt="스크린샷 2023-02-08 오전 1 43 57" src="https://user-images.githubusercontent.com/71378447/217323444-6e7b5989-e8b3-48d5-b2f3-2d14589784c4.png">


- 참고 : 아래 그림에서 
Reader 밑에 readcount는 공유 변수다. 따라서 readcount를 동시에 접근하여 문제가 발생하지 않도록 P(mutex)와 V(mutex)를 readcount를 사용하는 앞뒤로 추가한다.

<img width="608" alt="스크린샷 2023-02-08 오전 1 44 08" src="https://user-images.githubusercontent.com/71378447/217323473-8d5a6640-38c8-4338-81a0-624cbc477de1.png">

### 3. Dining Philosophers Problem

<img width="606" alt="스크린샷 2023-02-08 오전 2 13 00" src="https://user-images.githubusercontent.com/71378447/217323512-befab5f5-df06-4fee-a291-02ff0a9b5651.png">


- 위의 사진 코드의 문제점
  - Deadlock 가능성이 있다
  - 모든 철학자가 동시에 배가 고파져 왼쪽 젓가락을 집어버린 경우
  
- 해결 방안
  - 4명의 철학자만이 테이블에 동시에 앉을 수 있도록 한다.
  - 젓가락을 두 개 모두 집을 수 있을 때에만 젓가락을 집을 수 있게 한다.
  - 비대칭
    - 짝수(홀수) 철학자는 왼쪽(오른쪽) 젓가락부터 집도록 한다.
    
  
<img width="604" alt="스크린샷 2023-02-08 오전 2 19 29" src="https://user-images.githubusercontent.com/71378447/217323611-54776a5a-6a0a-4ba9-8e7d-32abe73d7558.png">

    

## Monitor

- 세마포어의 문제점
  - 코딩하기 힘들다
  
  - 정확성의 입증이 어렵다
  - 자발적 협력이 필요하다
  - 한 번의 실수가 모든 시스템에 치명적 영향
  
  
 ### 세마포어를 사용한 실수 예시
 <img width="545" alt="스크린샷 2023-02-08 오전 2 37 39" src="https://user-images.githubusercontent.com/71378447/217323717-d2154bf1-e66f-4b0b-bb44-bdad4c990263.png">


  ### 모니터 구현 상세
  
<img width="607" alt="스크린샷 2023-02-08 오전 2 39 10" src="https://user-images.githubusercontent.com/71378447/217323755-2049e014-7ebf-4ca2-be42-2a511605f318.png">


<img width="609" alt="스크린샷 2023-02-08 오전 2 39 56" src="https://user-images.githubusercontent.com/71378447/217323773-8df31340-47c9-4234-a92d-39c65a9b13b7.png">


<img width="603" alt="스크린샷 2023-02-08 오전 2 41 15" src="https://user-images.githubusercontent.com/71378447/217323787-7cad9193-133e-4b39-8ff4-b8f40f080efc.png">

<img width="606" alt="스크린샷 2023-03-15 오후 5 55 31" src="https://user-images.githubusercontent.com/71378447/225257576-cb2b8155-a304-4de3-b83f-fb51fc445d47.png">

- 위 그림에 나온 코드에 대한 간단한 설명
  - 모니터 안에 produce와 consume
  
  - produce
    - 만약 empty buffer가 없으면 empty.wait()를 함.
    - 그 후 empty.signal()을 받거나 empty buffer가 있으면 x를 buffer에 추가함
    - buffer에 데이터가 들어오기를 기다리고 있는 쓰레드가 있을 수 있으므로 full.signal()
  
  - consume은 produce와 반대
  
  
### 식사하는 철학자 문제를 모니터로 해결하는 코드
  
  
<img width="608" alt="스크린샷 2023-03-15 오후 8 16 59" src="https://user-images.githubusercontent.com/71378447/225293573-ff4a0758-d9aa-4e53-a2d5-116dcf4e2bb6.png">

  
