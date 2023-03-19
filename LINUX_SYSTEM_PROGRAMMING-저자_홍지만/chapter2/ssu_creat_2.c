//
// Created by jinho on 2023/03/16.
//

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int main(void) {
    // fname : 만들 텍스트 파일의 이름
    char *fname = "ssu_test.txt";
    int fd;

    if ((fd = creat(fname, 0666)) < 0) {
        // 파일 생성에 실패한 경우
        fprintf(stderr, "creat error for %s\n", fname);
        exit(1);
    }
        // 파일 생성에 성공한 경우
    else {
        close(fd);
        fd = open(fname, O_RDWR);
        printf("Succeeded!\n<%s> is new readable ad writable\n", fname);
    }

    exit(0);
}
