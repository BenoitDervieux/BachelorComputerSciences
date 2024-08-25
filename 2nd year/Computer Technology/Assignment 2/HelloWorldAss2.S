@
@Assembler program print out "Hello World"
@using the Pico SDK
@
@R0 - first parameter
@R1 - second parameter to printer
@R7 - index counter
@

.thumb_func
.global main

main:
	MOV R7, #100
	BL stdio_init_all
loop:
	LDR R0, =helloworld
	CMP R7, #0
	BEQ reset
	MOV R1, R7
	BL printf
	SUB R7, #1
	B loop
reset:
	mov r7, #100
	B loop

.data
	.align 4
helloworld: .asciz "Hello World %d\n"

