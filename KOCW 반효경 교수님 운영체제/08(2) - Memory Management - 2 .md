# Memory Management - 2

## Multilevel paging and Performance

- Address space가 더 커지면 다단계 페이지 테이블 필요

- 각 단계의 페이지 테이블이 메모리에 존재하므로 logical address의 physical address 변환에 더 많은 메모리 접근 필요
- TLB를 통해 메모리 접근 시간을 줄일 수 있음

## Valid / Invalid Bit in Page Table 

- page table에는 사용되지 않는 page를 위한 page entry도 함께 존재해야 하기 때문에 실제로 사용되지 않는 페이지는 invalid하다고 표시해야 함.

<img width="604" alt="스크린샷 2023-05-03 오후 9 38 13" src="https://user-images.githubusercontent.com/71378447/235926672-352d7a11-f649-4a85-842a-5015e7e9d478.png">


## Memory Protection

- page table의 각 entry마다 아래의 bit를 둔다

  - protection bit
    - page에 대한 접근 권한(read/write/read-only)
  
  - valid-invalid bit
    - valid는 해당 주소의 frame에 그 프로세스를 구성하는 유효한 내용이 있음을 뜻함 (접근 허용)
    - invalid는 해당 주소의 frame에 유효한 내용이 없음을 뜻함 (접근 불허)
    
    
## Inverted Page Table

<img width="604" alt="스크린샷 2023-05-03 오후 9 47 39" src="https://user-images.githubusercontent.com/71378447/235926703-7dd1a8d0-161d-4664-b1c1-ed675453c9e2.png">

<img width="606" alt="스크린샷 2023-05-03 오후 9 49 08" src="https://user-images.githubusercontent.com/71378447/235926750-3e28d85a-c349-45fe-829b-995834dc0d94.png">


## Shared Page

- Shared code
  - Re-entrant Code (=Pure code)
  - read-only로 하여 프로세스 간에 하나의 code만 메모리에 올림(ex. text editors, compilers, window systems)
  
  - shared code는 모든 프로세스의 logical address space에서 동일한 위치에 있어야 함.
  
  
- Private code and data
  - 각 프로세스들은 독자적으로 메모리에 올림
  
  - private data는 logical address space의 아무 곳에 와도 무방
  
<img width="606" alt="스크린샷 2023-05-03 오후 10 02 34" src="https://user-images.githubusercontent.com/71378447/235926793-bbc03bbb-a3a7-41f1-9bd8-31ab92333c8c.png">


## Segmentation

- 프로그램은 의미 단위인 여러개의 segment로 구성
  - 작게는 프로그램을 구성하는 함수 하나하나를 세그먼트로 정의
  - 크게는 프로그램 전체를 하나의 세그먼트로 정의 가능
  - 일반적으로는 **code, data, stack** 부분이 하나의 세그먼트로 정의됨
  
  - Segment는 다음과 같은 logical unit들이다
    - main()
    - function
    - global variables,
    - stack,
    symbol table, arrays


## Segmentation Architecture

<img width="608" alt="스크린샷 2023-05-03 오후 10 06 11" src="https://user-images.githubusercontent.com/71378447/235926838-cd6a6316-d702-48e5-bb57-19920088a43f.png">

<img width="605" alt="스크린샷 2023-05-03 오후 10 12 06" src="https://user-images.githubusercontent.com/71378447/235926877-a55935b9-bcba-4f87-8b3f-93e90485b3d7.png">

## Example of Segmentation

<img width="608" alt="스크린샷 2023-05-03 오후 10 26 19" src="https://user-images.githubusercontent.com/71378447/235936706-bbe30985-a201-4dd8-85f6-d5523fc0ef84.png">

## Sharing of Segments

<img width="602" alt="스크린샷 2023-05-03 오후 10 36 36" src="https://user-images.githubusercontent.com/71378447/235936762-4d335a4b-84e2-4fb5-a323-6169746baa3b.png">

## Segmentation with Paging

<img width="606" alt="스크린샷 2023-05-03 오후 10 46 03" src="https://user-images.githubusercontent.com/71378447/235936828-18df075e-dd20-4151-93dc-5f584abb45bc.png">

