<template>
  <div class="q-pa-md">
    <div class="q-gutter-md">
      <q-carousel
        v-model="slide"
        transition-prev="slide-right"
        transition-next="slide-left"
        animated
        control-color="primary"
        class="rounded-borders"
      >
        <q-carousel-slide
          name="style"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="style"
            color="primary"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="tv"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="live_tv"
            color="primary"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="layers"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="layers"
            color="primary"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="map"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="terrain"
            color="primary"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
      </q-carousel>

      <div class="row justify-center">
        <q-btn-toggle
          glossy
          v-model="slide"
          :options="options"
        />
      </div>
      <q-separator color="red" />

      <q-carousel
        v-model="slide"
        transition-prev="scale"
        transition-next="scale"
        swipeable
        animated
        control-color="white"
        navigation
        padding
        arrows
        height="300px"
        class="bg-primary text-white shadow-1 rounded-borders"
      >
        <q-carousel-slide
          name="style"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="style"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="tv"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="live_tv"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="layers"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="layers"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="map"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="terrain"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
      </q-carousel>

      <q-carousel
        v-model="slide"
        transition-prev="jump-right"
        transition-next="jump-left"
        swipeable
        animated
        control-color="white"
        prev-icon="arrow_left"
        next-icon="arrow_right"
        navigation-icon="radio_button_unchecked"
        navigation
        padding
        arrows
        height="300px"
        class="bg-purple text-white shadow-1 rounded-borders"
      >
        <q-carousel-slide
          name="style"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="style"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="tv"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="live_tv"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="layers"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="layers"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="map"
          class="column no-wrap flex-center"
        >
          <q-icon
            name="terrain"
            size="56px"
          />
          <div class="q-mt-md text-center">
            {{ lorem }}
          </div>
        </q-carousel-slide>
      </q-carousel>
      <q-separator color="blue" />
      <q-carousel
        animated
        v-model="slide1"
        arrows
        navigation
        infinite
      >
        <q-carousel-slide
          :name="1"
          img-src="https://cdn.quasar.dev/img/mountains.jpg"
        />
        <q-carousel-slide
          :name="2"
          img-src="https://cdn.quasar.dev/img/parallax1.jpg"
        />
        <q-carousel-slide
          :name="3"
          img-src="https://cdn.quasar.dev/img/parallax2.jpg"
        />
        <q-carousel-slide
          :name="4"
          img-src="https://cdn.quasar.dev/img/quasar.jpg"
        />
      </q-carousel>

      <q-separator color="blue" />
      <q-carousel
        v-model="slide1"
        transition-prev="slide-right"
        transition-next="slide-left"
        swipeable
        animated
        control-color="primary"
        navigation
        padding
        arrows
        height="300px"
        class="bg-grey-1 shadow-2 rounded-borders"
      >
        <q-carousel-slide
          :name="1"
          class="column no-wrap"
        >
          <div class="row fit justify-start items-center q-gutter-xs q-col-gutter no-wrap">
            <q-img
              class="rounded-borders col-3 full-height"
              src="https://cdn.quasar.dev/img/mountains.jpg"
            />
            <q-img
              class="rounded-borders col-3 full-height"
              src="https://cdn.quasar.dev/img/parallax1.jpg"
            />
            <q-img
              class="rounded-borders col-3 full-height"
              src="https://cdn.quasar.dev/img/parallax2.jpg"
            />
            <q-img
              class="rounded-borders col-3 full-height"
              src="https://cdn.quasar.dev/img/quasar.jpg"
            />
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          :name="2"
          class="column no-wrap"
        >
          <div class="row fit justify-start items-center q-gutter-xs q-col-gutter no-wrap">
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/parallax2.jpg"
            />
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/quasar.jpg"
            />
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          :name="3"
          class="column no-wrap"
        >
          <div class="row fit justify-start items-center q-gutter-xs q-col-gutter no-wrap">
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/mountains.jpg"
            />
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/parallax1.jpg"
            />
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          :name="4"
          class="column no-wrap"
        >
          <div class="row fit justify-start items-center q-gutter-xs q-col-gutter no-wrap">
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/parallax2.jpg"
            />
            <q-img
              class="rounded-borders col-6 full-height"
              src="https://cdn.quasar.dev/img/quasar.jpg"
            />
          </div>
        </q-carousel-slide>
      </q-carousel>
      <q-separator />
      <q-carousel
        arrows
        animated
        v-model="slide2"
        height="400px"
        transition-prev="slide-right"
        transition-next="slide-left"
        swipeable
        control-color="primary"
        padding
        class="bg-grey-1 shadow-2 rounded-borders"
      >
        <q-carousel-slide
          name="first"
          img-src="https://cdn.quasar.dev/img/mountains.jpg"
        >
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">First stop</div>
            <div class="text-subtitle1">Mountains</div>
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="second"
          img-src="https://cdn.quasar.dev/img/parallax1.jpg"
        >
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">Second stop</div>
            <div class="text-subtitle1">Famous City</div>
          </div>
        </q-carousel-slide>
        <q-carousel-slide
          name="third"
          img-src="https://cdn.quasar.dev/img/parallax2.jpg"
        >
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">Third stop</div>
            <div class="text-subtitle1">Famous Bridge</div>
          </div>
        </q-carousel-slide>
      </q-carousel>
      <q-separator />
      <q-carousel
        animated
        v-model="slide3"
        infinite
      >
        <q-carousel-slide name="soft-jazz">
          <q-video
            class="absolute-full"
            src="https://www.youtube.com/embed/k3_tw44QsZQ"
          />
        </q-carousel-slide>

        <q-carousel-slide name="Rihanna">
          <q-video
            class="absolute-full"
            src="https://www.youtube.com/embed/kOkQ4T5WO9E"
          />
        </q-carousel-slide>

        <q-carousel-slide name="chill-mix">
          <q-video
            class="absolute-full"
            src="https://www.youtube.com/embed/rQLhZlI6aTM"
          />
        </q-carousel-slide>
      </q-carousel>

      <div class="row justify-center">
        <q-btn-toggle
          glossy
          v-model="slide"
          :options="[
            { label: 'Soft Jazz', value: 'soft-jazz' },
            { label: 'Rihanna', value: 'Rihanna' },
            { label: 'Chill Mix', value: 'chill-mix' }
          ]"
        />
      </div>
      <q-separator />
      <q-carousel
        swipeable
        animated
        v-model="slide1"
        thumbnails
        infinite
      >
        <q-carousel-slide
          :name="1"
          img-src="https://cdn.quasar.dev/img/mountains.jpg"
        />
        <q-carousel-slide
          :name="2"
          img-src="https://cdn.quasar.dev/img/parallax1.jpg"
        />
        <q-carousel-slide
          :name="3"
          img-src="https://cdn.quasar.dev/img/parallax2.jpg"
        />
        <q-carousel-slide
          :name="4"
          img-src="https://cdn.quasar.dev/img/quasar.jpg"
        />
      </q-carousel>
      <q-separator />
      <q-carousel
        animated
        v-model="slide1"
        navigation
        infinite
        autoplay
        arrows
        transition-prev="slide-right"
        transition-next="slide-left"
      >
        <q-carousel-slide
          :name="1"
          img-src="https://cdn.quasar.dev/img/mountains.jpg"
        />
        <q-carousel-slide
          :name="2"
          img-src="https://cdn.quasar.dev/img/parallax1.jpg"
        />
        <q-carousel-slide
          :name="3"
          img-src="https://cdn.quasar.dev/img/parallax2.jpg"
        />
        <q-carousel-slide
          :name="4"
          img-src="https://cdn.quasar.dev/img/quasar.jpg"
        />
      </q-carousel>
      <q-separator />
      <q-carousel
        swipeable
        animated
        arrows
        v-model="slide1"
        :fullscreen.sync="fullscreen"
        infinite
      >
        <q-carousel-slide
          :name="1"
          img-src="https://cdn.quasar.dev/img/mountains.jpg"
        />
        <q-carousel-slide
          :name="2"
          img-src="https://cdn.quasar.dev/img/parallax1.jpg"
        />
        <q-carousel-slide
          :name="3"
          img-src="https://cdn.quasar.dev/img/parallax2.jpg"
        />
        <q-carousel-slide
          :name="4"
          img-src="https://cdn.quasar.dev/img/quasar.jpg"
        />

        <template v-slot:control>
          <q-carousel-control
            position="bottom-right"
            :offset="[18, 18]"
          >
            <q-btn
              push
              round
              dense
              color="white"
              text-color="primary"
              :icon="fullscreen ? 'fullscreen_exit' : 'fullscreen'"
              @click="fullscreen = !fullscreen"
            />
          </q-carousel-control>
        </template>
      </q-carousel>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      slide: 'style',
      slide1: 1,
      slide2: 'first',
      slide3: 'Rihanna',
      fullscreen: false,
      options: [
        { label: 1, value: 'style' },
        { label: 2, value: 'tv' },
        { label: 3, value: 'layers' },
        { label: 4, value: 'map' }
      ],
      lorem:
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Itaque voluptatem totam, architecto cupiditate officia rerum, error dignissimos praesentium libero ab nemo provident incidunt ducimus iusto perferendis porro earum. Totam, numquam?'
    }
  }
}
</script>
<style lang="sass" scoped>
.custom-caption
  text-align: center
  padding: 12px
  color: white
  background-color: rgba(0, 0, 0, .3)
</style>
