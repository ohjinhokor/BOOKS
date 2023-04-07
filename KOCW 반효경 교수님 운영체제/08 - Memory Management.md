# Memory Management


## Logical &nbsp; vs   &nbsp; Physical Address

- Logical address(=virtual address)
  - 프로세스마다 독립적으로 가지는 주소 공간
  - 각 프로세스마다 0번지부터 시작
  - CPU가 보는 주소는 logical address임
  
- Physical address
  - 메모리에 실제 올라가는 위치
  
- 주소 바인딩 : 주소를 결정하는 것
  - Symbolic Address -> Logical Address -> Physical address
  
  - **Logical Address가 Physical address로 매핑되는 과정이 어떻게 일어날까?-> 중요**
    - MMU가 Logical Address를 Physical address로 바꿔줌


## 주소 바인딩

- Compile time binding
  - 물리적 메모리 주소(physical address)가 컴파일 시 알려짐
  - 시작 위치 변경시 재컴파일
  
  - 컴파일러는 절대 코드(absolute code)생성
  
- Load time binding : ram에 프로그램을 올리는 시점에 바인딩 함
  - Loader의 책임하에 물리적 메모리 주소 부여
  - 컴파일러가 재배치가능코드(relocatable code)를 생성한 경우 가능
  
- **Execution time binding (= Run time binding)**
  - 수행이 시작된 이후에도 프로세스의 메모리 상 위치를 옮길 수 있음.
  - CPU가 주소를 참조할 때마다 binding을 점검(address mapping table)
  - 하드웨어적인 지원이 필요함(base and limit register, MMU)
  

<img width="612" alt="스크린샷 2023-03-29 오후 12 44 43" src="https://user-images.githubusercontent.com/71378447/230349312-8e0f4d16-6a62-4e2a-9d75-85f160b3a021.png">



** 현대 운영체제는 주로 run time binding을 사용함**
**따라서 cpu가 바라보는 주소는 logical address임!**


## MMU(Memory Management Unit)

- MMU(Memory Management Unit)
  - logical address를 physical address로 매핑해주는 Hardware device
  
- MMU scheme
  - 사용자 프로세스가 CPU에서 수행되며 생성해내는 모든 주솟값에 대해 base register(=relocation register)의 값을 더한다.


<img width="610" alt="스크린샷 2023-03-29 오후 12 55 58" src="https://user-images.githubusercontent.com/71378447/230349336-ee814888-16d8-491f-baaf-04c4ef663e8f.png">


<img width="608" alt="스크린샷 2023-03-29 오후 12 59 48" src="https://user-images.githubusercontent.com/71378447/230349357-528d9982-349b-45e5-a383-e2e2c91376fe.png">




- user program
  - logical address만을 다룬다.
  - 실제 physical address를 볼 수 없으며, 알 필요가 없다.
  

### Dynamic Loading

 - 프로세스 전체를 메모리에 미리 다 올리는 것이 아니라 해당 루틴이 불려질 때 메모리에 load하는 것
 
 - memory utilization의 향상
 
 - 가끔씩 사용되는 많은 양의 코드의 경우 유용
   - ex) 오류 처리 루틴
   
 - 운영체제의 특별한 지원 없이 프로그램 자체에서 구현 가능 (OS는 라이브러리를 통해 지원 가능)
 
 - Loading : 메모리로 올리는 것.
 
### Overlays (예전 이야기임)
  - 메모리에 프로세스의 부분 중 실제 필요한 정보만을 올림
  - 프로세스의 크기가 메모리보다 클 때 유용
  - 운영체제의 지원 없이 사용자에 의해 구현
  - 작은 공간의 메모리를 사용하던 초창기 시스템에서 수작업으로 프로그래머가 구현
    - Manual Overlay
    - 프로그래밍이 매우 복잡해짐


### Swapping - 개인적으로 중요한 개념이라고 생각함

- swapping 
  - 프로세스를 일시적으로 메모리에서 backing store로 쫓아내는 것.

- Backing store(=swap area)
  - 디스크
    - 많은 사용자의 프로세스 이미지를 담을 만큼 충분히 빠르고 큰 저장 공간
- Swap in / Swap out
  - 일반적으로 중기 스케줄러(swapper)에 의해 swap out 시킬 프로세스 선정
  - priority-based CPU scheduling algorithm
    - priority가 낮은 프로세스를 swapped out 시킴
    - priority가 높은 프로세스를 메모리에 올려 놓음
    
  - compile time 혹은 load time binding에서는 원래 메모리 위치로 swap in 해야함.
  
  - Execution time binding에서는 추후 빈 메모리 영역 아무 곳에나 올릴 수 있음
  
  - swap time은 대부분 transfer time(swap되는 양에 비례하는 시간)임
  
  
  <img width="603" alt="스크린샷 2023-04-06 오전 11 03 52" src="https://user-images.githubusercontent.com/71378447/230349399-d874589a-7b20-45a3-b4d5-f70bf3ed7630.png">


  

### Dynamic Linking

- Linking을 실행시간까지 미루는 기법

#### static linking
  - 라이브러리가 프로그램의 실행 파일 코드에 포함됨
  - 실행 파일의 크기가 커짐
  - 동일한 라이브러리를 각각의 프로세스가 메모리에 올리므로 메모리 낭비 (ex. printf함수의 라이브러리 코드)
  
  
#### Dynamic linking
  - 라이브러리가 실행시 연결(link)됨
  - 라이브러리 호출 부분에 라이브러리 루틴의 위치를 찾기 위한 stub이라는 작은 코드를 둠
  - 라이브러리가 이미 메모리에 있으면 그 루틴의 주소로 가고 없으면 디스크에서 읽어옴
  - 운영체제의 도움이 필요
  

## Allocation of Physical Memory

<img width="607" alt="스크린샷 2023-04-06 오전 11 25 00" src="https://user-images.githubusercontent.com/71378447/230349453-18897905-b43b-4a7d-b2c3-8233bee93f4d.png">


### Continuous Allocation

- Contiguous allocation
  - 고정분할(Fixed partition) 방식
    - 물리적 메모리를 몇 개의 영구적 분할(partition)로 나눔
    - 분할의 크기가 모두 동일한 방식과 서로 다른 방식이 존재
    - 분할당 하나의 프로그램 적재
    - 융통성이 없음
      - 동시에 메모리에 load되는 프로그램의 수가 고정됨
      - 최대 수행 가능 프로그램 크기 제한
    - internal fragmentation 발생 (external fragmentation도 발생)
    
  - 가변분할(Variable partition) 방식
    - 프로그램의 크기를 고려해서 할당
    - 분할의 크기, 개수가 동적으로 변함
    - 기술적 관리 기법 필요
    - External fragmentation 발생
    
  <img width="603" alt="스크린샷 2023-04-06 오전 11 39 44" src="https://user-images.githubusercontent.com/71378447/230349492-5ae29109-1196-4dab-8c4c-453f31f1b288.png">



<img width="603" alt="스크린샷 2023-04-06 오전 11 43 01" src="https://user-images.githubusercontent.com/71378447/230349525-1eef0d9f-9e80-4954-9b71-61ce3f2a0831.png">


 <img width="607" alt="스크린샷 2023-04-06 오전 11 44 52" src="https://user-images.githubusercontent.com/71378447/230349563-6b126765-29f9-42be-addf-e0ffe50c9661.png">


## Paging

- paging
  - process의 virtual memory를 동일한 사이즈의 page 단위로 나눔
  - virtual memory의 내용이 page 단위로 noncontiguous하게 저장됨
  - 일부는 backing storage에, 일부는 physical memory에 저장
  
- Basic Method
  - physical memory를 동일한 크기의 frame으로 나눔
  - logical memory를 동일 크기의 page로 나눔(frame과 같은 크기)
  - 모든 가용 frame들을 관리
  - page table을 사용하여 logical address를 physical address로 변환
  - External fragmentation 발생 안함
  - Internal fragmentation 발생 가능

### Paging Example

<img width="606" alt="스크린샷 2023-04-06 오후 7 32 32" src="https://user-images.githubusercontent.com/71378447/230564792-16f8290b-fad8-43e7-93ee-a1a9a4339308.png">

<img width="606" alt="스크린샷 2023-04-06 오후 7 35 31" src="https://user-images.githubusercontent.com/71378447/230564827-02838fe4-8a67-4cf9-84c5-9fce2bba0924.png">

### Implementation of Page Table

- Page table은 main memory에 상주함 
  - (개인적으로 궁금하던 부분이었는데 해결됨)
  
- **Page-table base register(PTBR)**가 page table을 가리킴
- **Page-table length register (PTLR)**가 테이블 크기를 보관

- 모든 메모리 접근 연산에는 2번의 memory access 필요
- page table 접근 1번, 실제 data/instruction 접근 1번
- 속도 향상을 위해 associative register 혹은 **translation look-aside buffer(TLB)**라 불리는 고속의 lookup hardware cache 사용

<img width="610" alt="스크린샷 2023-04-06 오후 7 42 48" src="https://user-images.githubusercontent.com/71378447/230564856-7471f026-a6d5-4341-9df0-6dbea78198ff.png">


### Associative Register

- Associative registers(TLB): parallel search가 가능
  - TLB에는 page table 중 일부만 존재
  
- Address translation
  - page table 중 일부가 associative register에 보관되어 있음
  - 만약 해당 page #가 associative register에 있는 경우 곧바로 frame #을 얻음
  - 그렇지 않은 경우 main memory에 있는 page table로부터 frame #을 얻음
  - TLB는 context switch 때 flush (remove old entries)


### Effective Access Time

<img width="602" alt="스크린샷 2023-04-06 오후 7 53 03" src="https://user-images.githubusercontent.com/71378447/230564874-0228c7d6-0154-4bf7-8389-c56a22a5700a.png">


### Two-Level Page Table (2단계 페이지 테이블)

<img width="607" alt="스크린샷 2023-04-06 오후 8 57 57" src="https://user-images.githubusercontent.com/71378447/230564896-590a63e7-3f8a-4451-b16a-dd1702265821.png">


<img width="606" alt="스크린샷 2023-04-06 오후 8 00 56" src="https://user-images.githubusercontent.com/71378447/230564909-46397111-0235-4835-9538-d280e699aaf5.png">


<img width="607" alt="스크린샷 2023-04-06 오후 9 05 03" src="https://user-images.githubusercontent.com/71378447/230564920-e989c73a-fc56-49e1-a53b-82fde7e8c64f.png">


