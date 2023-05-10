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
