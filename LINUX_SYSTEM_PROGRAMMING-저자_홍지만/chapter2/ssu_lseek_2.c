#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

#define CREATE_MODE (S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH)

char buf1[] = "1234567890";
char buf2[] = "ABCDEFGHIJ";

int main(void)
{
        char *fname = "ssu_hole.txt";
        int fd;

        if ((fd = creat(fname, CREATE_MODE)) < 0) {
                fprintf(stderr, "creat error for %s\n", fname);
                exit(1);
        }

        if (write(fd, buf1, 12) != 12) {
                fprintf(stderr, "bufi write error\n");
                exit(1);
        }

        if (lseek(fd, 15000, SEEK_SET) < 0) {
                fprintf(stderr, "Iseek error\n");
                exit(1);
        }

        if (write(fd, buf2, 12) != 12) {
                fprintf(stderr, "buf2 write error\n");
                exit(1);
        }

        exit(0);
}
