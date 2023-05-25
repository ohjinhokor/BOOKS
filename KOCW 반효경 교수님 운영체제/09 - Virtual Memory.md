# Virtual Memory

> 수업을 들어가기 전 : 물리 메모리의 주소 변환은 운영체제가 관여하지 않지만, **virtual memory 기법은 운영체제가 전적으로 관여한다.**


## Demand Paging

- 실제로 필요할 때 page를 메모리에 올리는 것
  - I/O 양의 감소
  - Memory 사용량 감소
  - 빠른 응답 시간
  - 더 많은 사용자 수용
  
- Valid/ Invalid bit의 사용
  - Invalid의 의미
    - 사용되지 않는 주소 영역인 경우
    - 페이지가 물리적 메모리에 없는 경우
  - 처음에는 모든 page entry가 invalid로 초기화
  - address translation시에 invalid bit가 set되어있으면 -> "page fault"
  
참고 ) page fault가 나는 것은 다시 말하면 trap이 실행되는 것임

<img width="526" alt="스크린샷 2023-05-10 오후 3 47 33" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/3d6a8c06-46fb-4846-94cd-6f93925072e0">

## Page Fault
- invalid page를 접근하면 MMU가 trap을 발생시킴(page fault trap)

- Kernel mode로 들어가서 page fault handler가 invoke됨
- 다음과 같은 순서로 page fault를 처리한다.
  - 1. Invalid reference? (ex. bad address, protection violation) => abort process
  - 2. Get an empty page frame (없으면 뺏어온다 : replace)
  - 3. 해당 페이지를 disk에서 memory로 읽어온다.
    - a) disk I/O가 끝나기까지 이 프로세스는 CPU를 preempt당함 (block)
    
    - b) Disk read가 끝나면 page tables entry기록. valid/invalid bit => "valid"로 변경
    - c) ready queue에 process를 insert -> dispatch later
  
  - 4. 이 프로세스가 CPU를 잡고 다시 running
  
  - 5. 아까 중단되었던 instruction을 재개
  
<img width="528" alt="스크린샷 2023-05-10 오후 4 02 25" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/7b6d690d-9ef9-4a63-bb97-df26c94e77b9">


## Free frame이 없는 경우

- Page replacement
  - 어떤 frame을 빼앗아올지 결정해야 함
  - 곧바로 사용되지 않을 page를 쫓아내는 것이 좋음
  
  - 동일한 페이지가 여러 번 메모리에서 쫓겨났다가 다시 들어올 수 있음
- Replacement Algorithm
  - page-fault rate를 최소화하는 것이 목표
  - 알고리즘의 평가
    - 주어진 page reference string에 대해 page fault를 얼마나 내는지 조사


## Page Replacement

  <img width="596" alt="스크린샷 2023-05-15 오전 12 46 02" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/bd4f439b-1736-478c-a15f-7aa72c31b9e0">


### Optimal Algorithm

- 가장 먼 미래에 참조되는 page를 알고있다고 **"가정"**합니다.
- 즉 현실에서는 사용할 수 없는 알고리즘임 (Offline algorithm)

- 다른 알고리즘의 성능에 대한 Upper bound를 제공하는 용도로 사용.

### FIFO(First In First Out) Algorithm

- 먼저 들어온 것을 먼저 내쫓음

### LRU (Least Recently Used) Algorithm

- 가장 오래 전에 참조된 것을 지움

### LFU (Least Frequently Used) Algorithm

- 참조 횟수가 가장 적은 페이지를 지움
  - 최저 참조 횟수인 page가 여럿 있는 경우
    - LFU 알고리즘 자체에서는 여러 page 중 임의로 선정한다.
    
- 장단점
  - LRU처럼 직전 참조 시점만 보는 것이 아니라 장기적인 시간 규모를 보기 때문에 page의 인기도를 더 정확히 반영할 수 있음
  - 참조 시점의 최근성을 반영하지 못함
  - LRU보다 구현이 
  

### LRU와 LFU 알고리즘의 구현

<img width="601" alt="스크린샷 2023-05-16 오전 1 02 10" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/50609d55-7d8b-4409-bc3a-b824021b59e1">



### Clock Algorithm

- LRU 알고리즘과 비슷함.

<img width="605" alt="스크린샷 2023-05-16 오전 2 08 00" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/89cd4845-f6fe-43c0-9b54-5d835c61f01b">

<img width="606" alt="스크린샷 2023-05-16 오전 2 08 13" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/e41871cf-478f-4f13-9dcc-da6ed915d051">


## Page Frame의 Allocation

- Allocation problem : 각 process에 얼마만큼의 page frame을 할당 할 것인가?

- Allocation의 필요성 
  - 메모리 참조 명령어 수행시 명령어, 데이터 등 여러 페이지 동시 참조
    - 명령어 수행을 위해 최소한 할당되어야 하는 frame수가 있음
  - Loop를 구성하는 page들은 한꺼번에 allocate되는 것이 유리함
    - 최소한의 allocation이 없으면 매 loop마다 page fault
    

- Allocation Scheme
  - Equal allocator : 모든 프로세스에 똑같은 갯수 할당
  - Proportional allocation : 프로세스 크기에 비례하여 할당
  - Priority allocator : 프로세스의 priority에 따라 다르게 할당
  

## Global vs Local Replacement

- Global replacement
  - Replace시 다른 process에 할당된 frame을 빼앗아 올 수 있다.
  - Process별 할당량을 조절하는 또 다른 방법임
  
  - FIFO, LRU, LFU 등의 알고리즘을 global replacement로 사용시에 해당
  - Working set, PFF 알고리즘 사용

- Local replacement
  - 자신에게 할당된 frame 내에서만 replacement
  
  - FIFO, LRU, LFU등의 알고리즘을 process별로 운영

## Thrashing

- 프로세스의 원활한 수행에 필요한 최소한의 page frame 수를 할당 받지 못한 경우 발생

- Page fault rate가 매우 높아짐
- CPU utilization이 낮아짐
- OS는 MPD(Multiprogramming degree)를 높여야 한다고 판단
- 또 다른 프로세스가 시스템에 추가됨(higher MPD)

### Thrashing Diagram

<img width="602" alt="스크린샷 2023-05-17 오후 7 36 27" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/1b666ba6-7f86-4fff-9160-663552d4faea">


## Working-Set Model

- Locality of reference
  - 프로세스는 특정 시간 동안 일정 장소만을 집중적으로 참조한다.
 
  - 집중적으로 참조되는 해당 page들의 집합을 locality set이라 함


- Working-set Model
  - Locality에 기반하여 프로세스가 일정 시간 동안 원활하게 수행되기 위해 한꺼번에 메모리에 올라와 있어야 하는 page들의 집합을 **Working Set**이라 정의함
  
  - Working Set 모델에서는 process의 working set 전체가 메모리에 올라와 있어야 수행되고, 그렇지 않을 경우 모든 frame을 반납한 후 swap out(suspend)
  - Thrashing 을 방지하기 위함
  - Multiprogramming degree를 결정함
  
### Working-Set Algorithm

<img width="600" alt="스크린샷 2023-05-17 오후 7 43 48" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/c5e2c01b-af9b-4231-a2bc-f3bc4403d3bf">

<img width="602" alt="스크린샷 2023-05-17 오후 7 44 13" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/8075c6bd-5f31-4b94-8a32-05ec3dca831a">


## PFF(Page Fault Frequency) Scheme

<img width="603" alt="스크린샷 2023-05-17 오후 7 47 25" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/72662c96-6f2f-4fdf-b1fc-c9b6bc021b78">


## Page Size의 결정

- page size를 감소시키면
  - 페이지 수 증가
  
  - 페이지 테이블 크기 증가
  - Internal fragmentation 감소
  - Disk transfer의 효율성 감소
    - Seek/rotation vs transfer
  - 필요한 정보만 메모리에 올라와 메모리 이용이 효율적
    - Localty의 활용 측면에서는 좋지 않음
  
  
- Trend
  - Larger page size
  



