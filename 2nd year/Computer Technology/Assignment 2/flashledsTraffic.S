.equ LED_PIN1, 0
.equ LED_PIN2, 1
.equ LED_PIN3, 2
.equ GPIO_OUT, 1
.equ sleep_time1, 1000
.equ sleep_time2, 5000

.thumb_func
.global main
main:
mov r0, #LED_PIN1
bl gpio_init
mov r0, #LED_PIN1
mov r1, #GPIO_OUT
bl link_gpio_set_dir
mov r0, #LED_PIN2
bl gpio_init
mov r0, #LED_PIN2
mov r1, #GPIO_OUT
bl link_gpio_set_dir
mov r0, #LED_PIN3
bl gpio_init
mov r0, #LED_PIN3
mov r1, #GPIO_OUT
bl link_gpio_set_dir
loop: mov r0, #LED_PIN1
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time2
bl sleep_ms
mov r0, #LED_PIN1
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIN2
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time1
bl sleep_ms
mov r0, #LED_PIN2
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIN3
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time2
bl sleep_ms
mov r0, #LED_PIN3
mov r1, #0
bl link_gpio_put
mov r0, #LED_PIN2
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time1
bl sleep_ms
mov r0, #LED_PIN2
mov r1, #0
bl link_gpio_put
b loop
