# Deadlock(교착 상태)

## The Deadlock Problem

- Deadlock
  - 일련의 프로세스들이 서로가 가진 자원을 기다리며 block된 상태
  
- Resource(자원)
  - 하드웨어, 소프트웨어 등을 포함하는 개념
  - 프로세스가 자원을 사용하는 절차
    - Request, Allocate, Use, Release
  

  - Deadlock Example
  
  <img width="370" alt="스크린샷 2023-03-15 오후 8 27 29" src="https://user-images.githubusercontent.com/71378447/225309107-b8e7afa1-e4b6-4653-8666-21898ecb8bb4.png">
  
## Deadlock 발생의 4가지 조건

- Mutual exclusion
  - 매 순간 하나의 프로세스만이 자원을 사용할 수 있음.
- No preemption(비선점)
  - 프로세스는 자원을 스스로 내어놓을 뿐 강제로 빼았기지 않음.
- Hold and Wait(보유 대기)
  - 자원을 가진 프로세스가 다른 자원을 기다릴 때 보유 자원을 놓지 않고 계속 가지고 있음.
  
- Circular wait(순환대기)
  - 자원을 기다리는 프로세스간에 싸이클이 형성됨
 
 
## Resource - Allocation Graph(자원 할당 그래프)
- 화살표 방향
  - 프로세스 -> 자원 (요청)
  - 자원 -> 프로세스 (사용 중)


<img width="606" alt="스크린샷 2023-03-15 오후 8 34 03" src="https://user-images.githubusercontent.com/71378447/225309163-eeff32c2-c26e-4541-a386-3aca374ddca3.png">




## Deadlock의 처리 방법
> Prevention, Avoidance : 미연에 방지
> Detection and Recovery : 시스템이 느려지면 그 때 검사


- Daedlock Prevention
  - 자원 할당 시 Deadlock의 4가지 필요 조건 중 어느 하나가 만족되지 않도록 하는 것
  
- Deadlock Avoidance
  - 자원 요청에 대한 부가적인 정보를이용해서 deadlock의 가능성이 없는 경우에만 자원을 할당
  - 시스템 state가 원래 state로 돌아올 수 있는 경우에만 자원 할당
  
- Deadlock Detection and recovery
  - Deadlock 발생은 허용하되, 그에 대한 Detection 루틴을 두어 deadlock 발견시 recover
  
- Deadlock Ignorance
  - Deadlock을 시스템이 책임지지 않음
  - UNIX를 포함한 대부분의 OS가 채택

### Deadlock Prevention
- Mutual Exclusion
  - 공유해서는 안되는 자원의 경우 반드시 성립하게 됨.
  
- Hold and wait
  - 프로세스가 자원을 요청할 때 다른 어떤 자원도 가지고 있지 않도록 함
    - 방법 1 : 프로세스 시작 시 모든 필요한 자원을 할당받게 하는 방법
    - 방법 2 : 자원이 필요한 경우 보유 자원을 모두 놓고 다시 요청
    
- No Preemption
  - process가 어떤 자원을 기다려야 하는 경우 이미 보유한 자원이 선점됨
  
  - 모든 필요한 자원을 얻을 수 있을 때 그 프로세스가 다시 시작 됨
  - state를 쉽게 save하고 restore 할 수 있는 자원에서 주로 사용하는 방법(ex. CPU, memory)
  
- Circular Wait
  - 모든 자원 유형에 할당 순서를 정하여 정해진 순서대로만 자원 할당
  - 예를 들어 순서가 3인 자원 R3을 보유 중인 프로세스가 순서가 1인 자원 R1을 할당받기 위해서는 우선 R3를 release 해야함
  
  
-> Deadlock을 예방하는 방법들은 성능을 저하시킴(Utilization 저하, throughput 감소, starvation문제)


### Deadlock Avoidance

- Deadlock avoidance
  - 자원 요청에 대한 부가정보를 이용하여 자원 할당이 deadlock으로부터 안전한(safe)한지를 동적으로 조사해서 안전한 경우에만 할당
  - 가장 단순하고 일반적인 모델은 프로세스들이 필요로하는 각 자원별 최대 사용량을 미리 선언하도록 하는 방법임

- safe state
  - 시스템 내의 프로세스들에 대한 safe sequence가 존재하는 상태
  
- safe sequence
  - 프로세스의 sequence <p1, p2, p3 ... pn>이 safe 하려면 pi(1<=i<=n)의 자원 요청이 "가용 자원 + 모든 pj(j<i)의 보유자원"에 의해 충족되어야 함
  
  - 조건을 만족하면 다음 방법으로 모든 프로세스의 수행을 보장
    - pi의 자원 요청이 즉시 충족될 수 없으면 모든 pi(j<i)가 종료될 때까지 기다린다
    - p(i-1)이 종료되면 pi의 자원요청을 만족시켜 수행한다.
    

<img width="606" alt="스크린샷 2023-03-15 오후 9 17 37" src="https://user-images.githubusercontent.com/71378447/225309222-56348bba-ed17-4fbc-ab71-57a860d91239.png">



### Resource Allocation Graph algorithm


<img width="608" alt="스크린샷 2023-03-15 오후 9 21 37" src="https://user-images.githubusercontent.com/71378447/225309252-e9171606-8aac-4d02-8506-7b9b5f9c7de7.png">


### Banker's Algorithm

<img width="604" alt="스크린샷 2023-03-15 오후 9 27 34" src="https://user-images.githubusercontent.com/71378447/225309277-e2cbf7cf-c30a-4e4a-8bf1-d031f24e7ad7.png">



<img width="605" alt="스크린샷 2023-03-15 오후 9 24 49" src="https://user-images.githubusercontent.com/71378447/225309308-822be35b-1e3a-4a25-8bec-2036781d16b5.png">


<img width="606" alt="스크린샷 2023-03-15 오후 9 25 43" src="https://user-images.githubusercontent.com/71378447/225309334-efc3c3ad-11f1-4e8b-86aa-5178cdefb040.png">


### Deadlock Detection and recovery

- Deadlock Detection
  - Resource type당 single instance인 경우
    - 자원 할당 그래프에서의 cycle이 곧 deadlock을 의미
  - resource type당 multiple instance인 경우
    - Banker's algorithm과 유사한 방법 활용

- wait for graph 알고리즘
  - Resource type당 single instance인 경우
  - wait-for graph
    - 자원 할당 그래프의 변형
    - 프로세스만으로 node 구성
    - Pi가 가지고 있는 자원을 Pk가 기다리는 경우 : Pk -> Pi
  - Algorithm
    - wait-for graph에 사이클이 존재하는지를 주기적으로 조사
    - 시간복잡도 : n제곱
    
  
<img width="605" alt="스크린샷 2023-03-15 오후 9 42 49" src="https://user-images.githubusercontent.com/71378447/225315062-85634a26-9671-41f4-8613-5fb4e0acfc4c.png">

<img width="598" alt="스크린샷 2023-03-15 오후 9 50 00" src="https://user-images.githubusercontent.com/71378447/225315112-c8b9210b-9faa-4cd1-a115-0089d2ef3c97.png">

<img width="607" alt="스크린샷 2023-03-15 오후 9 52 42" src="https://user-images.githubusercontent.com/71378447/225315144-624826af-d2e2-4449-8003-f1f7634f26f6.png">



- Recovery
  - Process termination
    - Abort all deadlocked processes
    - Abort one process at a time until the deadlock cycle is eliminated
    
  - Resource Preemption
    - 비용을 최소화 할 victim의 선정
    - safe state로 rollback하여 process를 restart
    - Starvatioin문제
      - 동일한 프로세스가 계속해서 victim으로 선정되는 경우
      - cost factor에 rollback 횟수도 같이 고려
