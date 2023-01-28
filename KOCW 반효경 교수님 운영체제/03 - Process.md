# Process


## 프로세스의 개념

- process is a program in execution

## 프로세스의 문맥(context)


> ex) Program Counter가 프로세스 주소공간의 code의 어느 부분을 가리키고 있는가를 보면 문맥을 알 수 있음

<img width="828" alt="스크린샷 2023-01-24 오후 9 26 46" src="https://user-images.githubusercontent.com/71378447/214326416-a774fb33-737a-4d01-a75c-1e13a52f529e.png">


- CPU 수행 상태를 나타내는 하드웨어 문맥
  - Program Counter

    
  - 각종 register
  
- 프로세스 주소 공간
  - code, data, stack
  
- 프로세스 관련 커널 자료 구조
  - PCB(Process Control Block)
  - Kernel stack
  
&nbsp;

## 프로세스의 상태

- 프로세스는 상태(state)가 변경되며 수행된다.

  - Running
  
    - CPU를 잡고 instruction을 수행 중인 상태
  - Ready
    - CPU를 기다리는 상태(메모리 등 다른 조건을 모두 만족하고)
  - Blocked(wait, sleep)
    - CPU를 주어도 당장 instruction을 수행할 수 없는 상태
    - Process 자신이 요청한 event(예: I/O)가 즉시 만족되지 않아 이를 기다리는 상태
    - ex) 디스크에서 file을 읽어와야 하는 경우
    
  - New : 프로세스가 생성중인 상태
  - Terminated : 수행이 끝난 상태
  
  - **Suspended**
    - 외부적인 이유로 프로세스의 수행이 정지된 상태
    - Medium-Term scheduler의 스와핑 때문에 새롭게 정의된 프로세스의 상태이다
    - 프로세스는 통째로 디스크에 swap out된다
    - ex) 
      - 사용자가 프로그램을 일시 정지시킨 경우(break key)
      - 시스템이 여러 이유로 프로세스를 잠시 중단시킴(메모리에 너무 많은 프로세스가 올라와 있을 때)
  

- Blocked : 자신이 요청한 event가 만족되면 ready

- Suspended : 외부에서 resume 해주어야 active

## 프로세스 상태도

<img width="820" alt="스크린샷 2023-01-24 오후 9 38 30" src="https://user-images.githubusercontent.com/71378447/214326500-d3000545-3d59-4ca4-bbe0-e9075751218d.png">

## 프로세스 상태도 (with suspend)

<img width="825" alt="스크린샷 2023-01-24 오후 11 47 36" src="https://user-images.githubusercontent.com/71378447/214326581-dcfa2225-41bc-494f-b0bc-6b300e391de5.png">


## 프로세스의 상태

<img width="820" alt="스크린샷 2023-01-24 오후 9 48 42" src="https://user-images.githubusercontent.com/71378447/214326633-89705d70-1c08-45e4-9ec1-9fd693024a53.png">


## PCB

<img width="821" alt="스크린샷 2023-01-24 오후 10 05 06" src="https://user-images.githubusercontent.com/71378447/214326656-10884882-ebd7-4b93-831c-f3296d3359e2.png">



## 문맥 교환(Context Switching)
- CPU를 한 프로세스에서 다른 프로세스로 넘겨주는 과정

- CPU가 다른 프로세스에게 넘어갈 때 운영체제는 다음을 수행
  - CPU를 내어주는 프로세스의 상태를 그 프로세스의 PCB에 저장
  - CPU를 새롭게 얻는 프로세스의 상태를 PCB에서 읽어옴



<img width="785" alt="스크린샷 2023-01-24 오후 10 08 34" src="https://user-images.githubusercontent.com/71378447/214326815-92d9997b-29bd-490b-80d1-ce81063b2fd7.png">

<img width="824" alt="스크린샷 2023-01-24 오후 10 13 35" src="https://user-images.githubusercontent.com/71378447/214326899-1960ddf6-a07b-4d3a-9d6c-2b6764a8978c.png">


## 프로세스를 스케줄링하기 위한 큐

- Job Queue

  - 현재 시스템 내에 있는 모든 프로세스의 집합
- Ready Queue
  - 현재 메모리 내에 있으면서 CPU를 잡아서 실행되기를 기다리는 프로세스의 집합
  
- Device Queues
  - I/O Device의 처리를 기다리는 프로세스의 집합
  
- 프로세스들은 각 큐들을 오가며 수행된다


## Ready Queue와 다양한 Device Queue

<img width="1163" alt="스크린샷 2023-01-24 오후 10 29 22" src="https://user-images.githubusercontent.com/71378447/214327052-4ba3c72b-72be-4dfb-a14b-5191c58ad79f.png">


## 프로세스 스케줄링 큐의 모습


<img width="1177" alt="스크린샷 2023-01-24 오후 10 31 30" src="https://user-images.githubusercontent.com/71378447/214327099-f25937f7-2dd3-4e3f-ab72-824c9f91aec2.png">


## 스케줄러(scheduler)
- Long-term scheduler(장기 스케줄러 or job scheduler)

  - 시작 프로세스 중 어떤 것들을 ready queue로 보낼지 결정
  - 프로세스에 memory(및 각종 자원)을 주는 문제
  - 메모리에 프로그램을 몇 개 올려놓을지 결정하는 문제를 다룸
  
  - degree of Multiprogramming을 제어
  - **time sharing system에는 보통 장기 스케줄러가 없음(무조건 ready)**  -> 따라서 현재 운영체제에서는 없다고 봐도 무방하지 않을까 싶음
  
  
- Short-term scheduler(단기 스케줄러 or CPU scheduler)
  - 어떤 프로세스를 다음번에 running 시킬지 결정
  - 프로세스에 CPU를 주는 문제
  - 충분히 빨라야 함(millisecond 단위)
  
- Medium-term Scheduler(중기 스케줄러 or swapper)
  - 여유 공간 마련을 위해 프로세스를 통째로 메모리에서 디스크로 쫓아냄
  - 프로세스에게서 memory를 뺐는 문제
  - degree of Multiprogrammiing을 제어
  
  
  ## Thread

> A **thread** (or lightweight process) is a basic unit of CPU utilizatioon

- Thread의 구성
  - program counter
  - register set
  - stack space
  
  
- Thread가 동료 thread와 공유하는 부분 
  - code section
  - data section
  - OS resources
  
- 전통적인 개념의 heavyweight process는 하나의 thread를 가지고 있는 task로 볼 수 있다.


<img width="609" alt="스크린샷 2023-01-28 오후 5 02 44" src="https://user-images.githubusercontent.com/71378447/215254822-c6c6177e-956b-4124-9d58-525b054b7cdc.png">


  
- 아래 사진에서 알 수 있듯이 PCB의 register와 program counter는 여러 쓰레드의 register와 program counter를 담고 있다.


<img width="608" alt="스크린샷 2023-01-28 오후 5 03 06" src="https://user-images.githubusercontent.com/71378447/215254825-366ed874-ea32-40c3-b3f2-9e36c5c13a23.png">



## 스레드의 장점

- 다중 쓰레드로 구성된 태스크 구조에서는 하나의 서버 스레드가 blocked(waiting) 상태인 동안에도 동일한 태스크 내의 다른 스레드가 실행되어 빠른 처리를 할 수 있다.

- 동일한 일을 수행하는 다중 스레드가 협력하여 높은 처리율(throughput)과 성능 향상을 얻을 수 있다.

- 스레드를 사용하면 병렬성을 높일 수 있다.


&nbsp;


<img width="609" alt="스크린샷 2023-01-28 오후 5 15 23" src="https://user-images.githubusercontent.com/71378447/215255467-71fb0ade-8cea-4d10-bf6a-bd7a9263646a.png">


<img width="606" alt="스크린샷 2023-01-28 오후 5 12 28" src="https://user-images.githubusercontent.com/71378447/215255398-1093e362-b9b5-407b-a3f0-50308958bcb0.png">


<img width="606" alt="스크린샷 2023-01-28 오후 5 21 15" src="https://user-images.githubusercontent.com/71378447/215255476-bcccc2c0-c97b-4690-bf4b-c9fe67f2b865.png">
