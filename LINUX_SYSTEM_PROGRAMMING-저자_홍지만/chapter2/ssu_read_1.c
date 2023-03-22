#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include "ssu_employee.h"


int main(int argc, char *argv[])
{
        struct ssu_employee record;
        int fd;
        int record_num;

        if (argc < 2) {
                fprintf(stderr, "Usage : %s file\n", argv[0]);
                exit(1);
        }

        if ((fd = open(argv[1], O_RDONLY)) < 0) {
                fprintf(stderr, "open error for %s\n", argv[1]);
                exit(1);
        }

        while (1) {
                printf("Enter record number : ");
                scanf("%d", &record_num);

                if (record_num < 0) break;

                if (lseek(fd, (long)record_num * sizeof(record), 0) < 0) {
                        fprintf(stderr, "lseek error");
                        exit(1);
                }

                if (read(fd, (char *)&record, sizeof(record)) > 0)
                        printf("Employee : %s Salary : %d\n", record.name, record.salary);
                else
                        printf("Record %d not found\n", record_num);
        }

        close(fd);
        exit(0);
}
