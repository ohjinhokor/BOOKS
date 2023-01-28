# CPU Scheduling

## CPU and I/O Bursts in Program Execution

- CPU 만 사용하고 있는 단계를 CPU burst라고 하고, I/O만 사용하고 있는 단계를 I/O burst라고 한다.

<img width="611" alt="스크린샷 2023-01-28 오후 6 49 22" src="https://user-images.githubusercontent.com/71378447/215261160-73e393f0-f657-4ea6-8676-7fcc52fcd620.png">



## CPU-burst Time의 분포

- 아래 사진에서 알 수 있는 점은 CPU만 계속 사용하는 프로그램은 소수이고 대부분이 I/O 작업과 cpu 작업이 번갈아 일어나는 경우(= I/O bound job) 가 훨씬 많았다는 것이다

<img width="608" alt="스크린샷 2023-01-28 오후 6 52 22" src="https://user-images.githubusercontent.com/71378447/215261231-7a257843-dfdd-4d89-9f45-78e7d92afa25.png">


- 여러 종류의 job(=process)가 섞여 있기 때문에 CPU 스케줄링이 필요하다

  - Interactive job에게 적절한 reponse 제공 요망
  - CPU와 I/O 장치 등 시스템 자원을 골고루 효율적으로 사용
  

## 프로세스의 특성 분류

- 프로세스는 그 특성에 따라 다음 두 가지로 나눔
  - I/O bound process
    - CPU를 잡고 계산하는 시간보다 I/O에 많은 시간이 필요한 job
    - (many short CPU bursts)
  
  
  - CPU-bound process
    - 계산 위주의 job
    - (few very long CPU bursts)
    
## CPU scheduler & Dispatcher

- CPU scheduler와 Dispatcher는 운영체제 안에 코드로 작성되어있음.

<img width="611" alt="스크린샷 2023-01-28 오후 7 06 22" src="https://user-images.githubusercontent.com/71378447/215261238-1780b383-1f55-46cf-bce7-5db70b6ee3c4.png">


## Scheduling Criteria (성능 척도)

- 시스템 입장에서의 성능 척도
  - CPU utilizatioin (이용률)
    - cpu가 놀지 않고 일한 시간

  - Throughput (처리량)
    - 주어진 시간 동안 몇개의 일을 처리했는가

- 프로그램 입장에서의 성능 척도

  - Turnaround time (소요시간, 반환시간)
  
    - cpu를 사용하기 위해 들어와서 다 쓰고 나갈 때까지의 시간
    - ex) I/O 작업이 끝난 후 대기하다가 cpu를 사용하고 반납하기 까지의 시간
    - 프로세스가 시작해서 종료되기까지의 시간이 아님. 프로세스가 cpu를 사용하기 위해 들어와서 I/O를 하기 위해 나갈 때까지의 시간임
  - Waiting time (대기 시간)
    - ready queue에서 기다리는 시간
  - Response time (응답 시간)
    - ready queue에 들어와서 처음으로 cpu를 얻기까지 걸린 시간

<img width="815" alt="스크린샷 2023-01-28 오후 7 36 51" src="https://user-images.githubusercontent.com/71378447/215264783-7488f379-9949-42ab-8ab6-4724c68eb774.png">


## FCFS (First-Come First-Served)

<img width="819" alt="image" src="https://user-images.githubusercontent.com/71378447/215264830-d30cac9f-a7e8-415c-8868-1fc7b8095bec.png">

<img width="816" alt="image" src="https://user-images.githubusercontent.com/71378447/215264839-17c4ec72-5181-48dc-9e48-e81d31898874.png">


## SJF (Shortest-Job-First)

- 기아 문제가 발생함
- CPU 사용 시간을 정확히 아는 것은 불가능 함


<img width="820" alt="image" src="https://user-images.githubusercontent.com/71378447/215264855-bddde5d2-fe91-4eb8-97e4-d8e00edf1289.png">

<img width="816" alt="image" src="https://user-images.githubusercontent.com/71378447/215264868-5d91a404-07b4-4701-8fd0-e2a362e6cb1e.png">


## Priority Scheduling

- 우선 순위가 가장 높은 프로세스에게 cpu를 할당함
- SJF도 Priority scheduling 중 하나이다

<img width="813" alt="image" src="https://user-images.githubusercontent.com/71378447/215264899-d3b0755d-ed33-4ff3-8a2b-5f1e2bcdafb0.png">

<img width="814" alt="image" src="https://user-images.githubusercontent.com/71378447/215264916-688ee1a6-c8a7-45b4-97a8-e3a16cae414d.png">


## Round Robin(RR)

- 현대 운영체제는 라운드 로빈에 기반함

<img width="813" alt="image" src="https://user-images.githubusercontent.com/71378447/215264932-59c0b12d-b813-4b2d-b73d-b940726b7e83.png">

<img width="816" alt="image" src="https://user-images.githubusercontent.com/71378447/215264947-83abfcf7-3839-4143-8b81-63ae0540c8a9.png">


