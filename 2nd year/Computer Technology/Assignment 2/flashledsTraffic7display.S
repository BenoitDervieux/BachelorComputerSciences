.equ LED_PINA, 0
.equ LED_PINB, 1
.equ LED_PINC, 2
.equ LED_PIND, 3
.equ LED_PINE, 4
.equ LED_PINF, 5
.equ LED_PING, 6
.equ GPIO_OUT, 1
.equ sleep_time, 1000

.thumb_func
.global main
main:
    mov r0, #LED_PINA
    bl gpio_init
    mov r0, #LED_PINA
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PINB
    bl gpio_init
    mov r0, #LED_PINB
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PINC
    bl gpio_init
    mov r0, #LED_PINC
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PIND
    bl gpio_init
    mov r0, #LED_PIND
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PINE
    bl gpio_init
    mov r0, #LED_PINE
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PINF
    bl gpio_init
    mov r0, #LED_PINF
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir
    mov r0, #LED_PING
    bl gpio_init
    mov r0, #LED_PING
    mov r1, #GPIO_OUT
    bl link_gpio_set_dir




loop:
bl zero
bl one
bl two
bl three
bl four
bl five
bl six
bl seven
bl eight
bl nine
b loop





@The zero
zero:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The one
one:
push {lr}
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc

@The two
two:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The three
three:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc

@The four
four:
push {lr}
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The five
five:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The six
six:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The seven
seven:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The eight
eight:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINE
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


@The nine
nine:
push {lr}
mov r0, #LED_PINA
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #1
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #1
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #1
bl link_gpio_put
mov r0, #LED_PING
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PINA
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINB
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINC
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIND
mov r1, #0
bl link_gpio_put
mov r0, #LED_PINF
mov r1, #0
bl link_gpio_put
mov r0, #LED_PING
mov r1, #0
bl link_gpio_put
pop {pc}
bx pc


