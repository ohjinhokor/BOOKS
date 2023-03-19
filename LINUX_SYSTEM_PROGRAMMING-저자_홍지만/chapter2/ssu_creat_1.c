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
        // creat 함수가 실패한 경우
        fprintf(stderr, "creat error for %s\n", fname);
        exit(1);
    } else
        // creat 함수가 성공한 경우
        printf("Success!\nFilename : %s\nDescriptor : %d\n", fname, fd);

    exit(0);
}
