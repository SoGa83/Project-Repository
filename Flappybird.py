
import pygame
import sys
import random

# Constants
SCREEN_WIDTH = 288
SCREEN_HEIGHT = 512
GRAVITY = 0.25
FLAP_POWER = 6.5
PIPE_GAP = 200

class Bird:
    def __init__(self):
        self.x = 50
        self.y = SCREEN_HEIGHT // 2
        self.velocity = 0

    def update(self):
        self.y += self.velocity
        self.velocity += GRAVITY

        if self.y < 0:
            self.y = 0
            self.velocity = 0
        elif self.y + 32 > SCREEN_HEIGHT:
            self.y = SCREEN_HEIGHT - 32
            self.velocity = 0

    def flap(self):
        self.velocity = -FLAP_POWER


class Pipe:
    def __init__(self, x):
        self.x = x
        self.height = random.randint(100, 300)
        self.gap_start = self.height
        self.gap_end = self.height + PIPE_GAP

    def update(self):
        self.x -= 2

    def is_offscreen(self):
        return self.x + 52 < 0


def check_collision(bird, pipes):
    bird_rect = pygame.Rect(bird.x, bird.y, 34, 24)

    for pipe in pipes:
        pipe_upper_rect = pygame.Rect(pipe.x, 0, 52, pipe.height)
        pipe_lower_rect = pygame.Rect(pipe.x, pipe.gap_end, 52, SCREEN_HEIGHT - pipe.gap_end)

        if bird_rect.colliderect(pipe_upper_rect) or bird_rect.colliderect(pipe_lower_rect):
            return True

    return False


def main():
    pygame.init()
    screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
    clock = pygame.time.Clock()

    bird = Bird()
    pipes = [Pipe(SCREEN_WIDTH + i * 160) for i in range(3)]

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
                bird.flap()

        bird.update()

        for pipe in pipes:
            pipe.update()
            if pipe.is_offscreen():
                pipe.__init__(pipe.x + 3 * 160)

        if check_collision(bird, pipes):
            break

        screen.fill((135, 206, 250))
        for pipe in pipes:
            pygame.draw.rect(screen, (0, 255, 0), (pipe.x, 0, 52, pipe.height))
            pygame.draw.rect(screen, (0, 255, 0), (pipe.x, pipe.gap_end, 52, SCREEN_HEIGHT - pipe.gap_end))
        pygame.draw.ellipse(screen, (255, 0, 0), (bird.x, bird.y, 34, 24))

        pygame.display.flip()
        clock.tick(60)


if __name__ == "__main__":
    main()