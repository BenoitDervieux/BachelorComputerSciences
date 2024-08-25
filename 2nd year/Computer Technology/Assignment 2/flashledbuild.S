.equ LED_PIN, 25
.equ GPIO_OUT, 1
.equ sleep_time, 1000

.thumb_func
.global main
main:
mov r0, #LED_PIN
bl gpio_init
mov r0, #LED_PIN
mov r1, #GPIO_OUT
bl link_gpio_set_dir

loop: mov r0, #LED_PIN
mov r1, #1
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
mov r0, #LED_PIN
mov r1, #0
bl link_gpio_put
ldr r0, =sleep_time
bl sleep_ms
b loop
