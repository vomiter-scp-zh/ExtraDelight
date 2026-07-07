# Extra Delight Item registry diff

Comparison basis: actual registry id inside register("...").

- vomiter active registry ids: 419
- lance 2.1.1 active registry ids: 746
- active in both: 398
- lance active but not active in vomiter: 348
  - exists in vomiter only as commented code: 347
  - no same registry id in vomiter: 1
- vomiter active but not in lance 2.1.1: 21
- vomiter commented but not in lance 2.1.1: 93

## Lance active, vomiter commented

| registry_id                         | lance_field | vomiter_field |
|-------------------------------------| --- | --- |
| ~~food_display~~                    | FOOD_DISPLAY | FOOD_DISPLAY |
| ~~baking_stone~~                    | BAKING_STONE | BAKING_STONE |
| jam                                 | JAM | JAM |
| glow_berry_jam                      | GLOW_BERRY_JAM | GLOW_BERRY_JAM |
| golden_apple_jam                    | GOLDEN_APPLE_JAM | GOLDEN_APPLE_JAM |
| jam_toast                           | JAM_TOAST | JAM_TOAST |
| glow_jam_toast                      | GLOW_JAM_TOAST | GLOW_JAM_TOAST |
| golden_jam_toast                    | GOLDEN_JAM_TOAST | GOLDEN_JAM_TOAST |
| buttered_toast                      | BUTTERED_TOAST | BUTTERED_TOAST |
| croutons                            | CROUTONS | CROUTONS |
| salad                               | SALAD | SALAD |
| salad_block_item                    | SALAD_FEAST_ITEM | SALAD_FEAST_ITEM |
| alfredo_sauce                       | ALFREDO_SAUCE | ALFREDO_SAUCE |
| pasta_tomato                        | PASTA_TOMATO | PASTA_TOMATO |
| pasta_alfredo                       | PASTA_ALFREDO | PASTA_ALFREDO |
| chicken_alfredo                     | CHICKEN_ALFREDO | CHICKEN_ALFREDO |
| chicken_parm                        | CHICKEN_PARM | CHICKEN_PARM |
| cheeseburger                        | CHEESEBURGER | CHEESEBURGER |
| bacon_cheeseburger                  | BACON_CHEESEBURGER | BACON_CHEESEBURGER |
| riceball                            | RICEBALL | RICEBALL |
| riceball_filled                     | RICEBALL_FILLED | RICEBALL_FILLED |
| fish_salad                          | FISH_SALAD | FISH_SALAD |
| fish_salad_sandwich                 | FISH_SALAD_SANDWICH | FISH_SALAD_SANDWICH |
| cactus                              | CACTUS | CACTUS |
| cooked_cactus                       | COOKED_CACTUS | COOKED_CACTUS |
| cactus_eggs                         | CACTUS_EGGS | CACTUS_EGGS |
| cactus_soup                         | CACTUS_SOUP | CACTUS_SOUP |
| cactus_salad                        | CACTUS_SALAD | CACTUS_SALAD |
| stuffed_cactus                      | STUFFED_CACTUS | STUFFED_CACTUS |
| corn_seeds                          | CORN_SEEDS | CORN_SEEDS |
| unshucked_corn                      | UNSHUCKED_CORN | UNSHUCKED_CORN |
| corn_on_cob                         | CORN_ON_COB | CORN_ON_COB |
| corn_husk                           | CORN_HUSK | CORN_HUSK |
| dried_corn_husk                     | DRIED_CORN_HUSK | DRIED_CORN_HUSK |
| corn_silk                           | CORN_SILK | CORN_SILK |
| corn_cob                            | CORN_COB | CORN_COB |
| corn_meal                           | CORN_MEAL | CORN_MEAL |
| corn_chowder                        | CORN_CHOWDER | CORN_CHOWDER |
| cream_corn                          | CREAM_CORN | CREAM_CORN |
| corn_fritters                       | CORN_FRITTERS | CORN_FRITTERS |
| popcorn                             | POPCORN | POPCORN |
| grilled_corn_on_cob                 | GRILLED_CORN_ON_COB | GRILLED_CORN_ON_COB |
| cooked_corn                         | COOKED_CORN | COOKED_CORN |
| roasted_pumpkin_seeds               | ROASTED_PUMPKIN_SEEDS | ROASTED_PUMPKIN_SEEDS |
| tea                                 | TEA | TEA |
| stewed_apples                       | STEWED_APPLES | STEWED_APPLES |
| apple_fritters                      | APPLE_FRITTERS | APPLE_FRITTERS |
| caramel_sauce                       | CARAMEL_SAUCE | CARAMEL_SAUCE |
| caramel_candy                       | CARAMEL_CANDY | CARAMEL_CANDY |
| candy_apple                         | CANDY_APPLE | CANDY_APPLE |
| caramel_apple                       | CARAMEL_APPLE | CARAMEL_APPLE |
| candy_golden_apple                  | CANDY_GOLDEN_APPLE | CANDY_GOLDEN_APPLE |
| caramel_golden_apple                | CARAMEL_GOLDEN_APPLE | CARAMEL_GOLDEN_APPLE |
| caramel_popcorn                     | CARAMEL_POPCORN | CARAMEL_POPCORN |
| caramel_custard                     | CARAMEL_CUSTARD | CARAMEL_CUSTARD |
| caramel_popsicle                    | CARAMEL_POPSICLE | CARAMEL_POPSICLE |
| flour_sack                          | FLOUR_SACK | FLOUR_SACK |
| cornmeal_sack                       | CORNMEAL_SACK | CORNMEAL_SACK |
| sugar_sack                          | SUGAR_SACK | SUGAR_SACK |
| corn_crate                          | CORN_CRATE | CORN_CRATE |
| corn_husk_bundle                    | CORN_HUSK_BUNDLE | CORN_HUSK_BUNDLE |
| dried_corn_husk_bundle              | DRIED_CORN_HUSK_BUNDLE | DRIED_CORN_HUSK_BUNDLE |
| corn_cob_bundle                     | CORN_COB_BUNDLE | CORN_COB_BUNDLE |
| breadcrumb_sack                     | BREADCRUMB_SACK | BREADCRUMB_SACK |
| egg_crate                           | EGG_CRATE | EGG_CRATE |
| apple_crate                         | APPLE_CRATE | APPLE_CRATE |
| golden_apple_crate                  | GOLDEN_APPLE_CRATE | GOLDEN_APPLE_CRATE |
| brown_mushroom_crate                | BROWN_MUSHROOM_CRATE | BROWN_MUSHROOM_CRATE |
| red_mushroom_crate                  | RED_MUSHROOM_CRATE | RED_MUSHROOM_CRATE |
| sweet_berry_crate                   | SWEET_BERRY_CRATE | SWEET_BERRY_CRATE |
| glow_berry_crate                    | GLOW_BERRY_CRATE | GLOW_BERRY_CRATE |
| corn_silk_sack                      | CORN_SILK_SACK | CORN_SILK_SACK |
| corn_cob_pipe                       | CORN_COB_PIPE | CORN_COB_PIPE |
| cinnamon_log                        | CINNAMON_LOG | CINNAMON_LOG |
| cinnamon_wood                       | CINNAMON_WOOD | CINNAMON_WOOD |
| stripped_cinnamon_log               | STRIPPED_CINNAMON_LOG | STRIPPED_CINNAMON_LOG |
| strippedcinnamon_wood               | STRIPPED_CINNAMON_WOOD | STRIPPED_CINNAMON_WOOD |
| cinnamon_planks                     | CINNAMON_PLANKS | CINNAMON_PLANKS |
| cinnamon_leaves                     | CINNAMON_LEAVES | CINNAMON_LEAVES |
| cinnamon_fence                      | CINNAMON_FENCE | CINNAMON_FENCE |
| cinnamon_fence_gate                 | CINNAMON_FENCE_GATE | CINNAMON_FENCE_GATE |
| cinnamon_stairs                     | CINNAMON_STAIRS | CINNAMON_STAIRS |
| cinnamon_door                       | CINNAMON_DOOR | CINNAMON_DOOR |
| cinnamon_trapdoor                   | CINNAMON_TRAPDOOR | CINNAMON_TRAPDOOR |
| cinnamon_cabinet                    | CINNAMON_CABINET | CINNAMON_CABINET |
| cinnamon_slab                       | CINNAMON_SLAB | CINNAMON_SLAB |
| cinnamon_button                     | CINNAMON_BUTTON | CINNAMON_BUTTON |
| cinnamon_pressure_plate             | CINNAMON_PRESSURE_PLATE | CINNAMON_PRESSURE_PLATE |
| cinnamon_bark                       | CINNAMON_BARK | CINNAMON_BARK |
| raw_cinnamon                        | RAW_CINNAMON | RAW_CINNAMON |
| raw_cinnamon_block                  | RAW_CINNAMON_BLOCK | RAW_CINNAMON_BLOCK |
| cinnamon_stick                      | CINNAMON_STICK | CINNAMON_STICK |
| cinnamon_stick_block                | CINNAMON_STICK_BLOCK | CINNAMON_STICK_BLOCK |
| ground_cinnamon                     | GROUND_CINNAMON | GROUND_CINNAMON |
| ice_cream                           | ICE_CREAM | ICE_CREAM |
| chocolate_ice_cream                 | CHOCOLATE_ICE_CREAM | CHOCOLATE_ICE_CREAM |
| glow_berry_ice_cream                | GLOW_BERRY_ICE_CREAM | GLOW_BERRY_ICE_CREAM |
| sweet_berry_ice_cream               | SWEET_BERRY_ICE_CREAM | SWEET_BERRY_ICE_CREAM |
| pumpkin_ice_cream                   | PUMPKIN_ICE_CREAM | PUMPKIN_ICE_CREAM |
| honey_ice_cream                     | HONEY_ICE_CREAM | HONEY_ICE_CREAM |
| apple_ice_cream                     | APPLE_ICE_CREAM | APPLE_ICE_CREAM |
| cookie_dough_ice_cream              | COOKIE_DOUGH_ICE_CREAM | COOKIE_DOUGH_ICE_CREAM |
| mint_chip_ice_cream                 | MINT_CHIP_ICE_CREAM | MINT_CHIP_ICE_CREAM |
| milkshake                           | MILKSHAKE | MILKSHAKE |
| chocolate_milkshake                 | CHOCOLATE_MILKSHAKE | CHOCOLATE_MILKSHAKE |
| glow_berry_milkshake                | GLOW_BERRY_MILKSHAKE | GLOW_BERRY_MILKSHAKE |
| sweet_berry_milkshake               | SWEET_BERRY_MILKSHAKE | SWEET_BERRY_MILKSHAKE |
| pumpkin_milkshake                   | PUMPKIN_MILKSHAKE | PUMPKIN_MILKSHAKE |
| honey_milkshake                     | HONEY_MILKSHAKE | HONEY_MILKSHAKE |
| apple_milkshake                     | APPLE_MILKSHAKE | APPLE_MILKSHAKE |
| cookie_dough_milkshake              | COOKIE_DOUGH_MILKSHAKE | COOKIE_DOUGH_MILKSHAKE |
| mint_chip_milkshake                 | MINT_CHIP_MILKSHAKE | MINT_CHIP_MILKSHAKE |
| chocolate_milk                      | CHOCOLATE_MILK | CHOCOLATE_MILK |
| eggnog                              | EGGNOG | EGGNOG |
| ginger_beer                         | GINGER_BEER | GINGER_BEER |
| horchata                            | HORCHATA | HORCHATA |
| apple_cookie_block_item             | APPLE_COOKIE_BLOCK | APPLE_COOKIE_BLOCK |
| chocolate_chip_cookie_block_item    | CHOCOLATE_CHIP_COOKIE_BLOCK | CHOCOLATE_CHIP_COOKIE_BLOCK |
| gingerbread_cookie_block_item       | GINGERBREAD_COOKIE_BLOCK | GINGERBREAD_COOKIE_BLOCK |
| glow_berry_cookie_block_item        | GLOW_BERRY_COOKIE_BLOCK | GLOW_BERRY_COOKIE_BLOCK |
| honey_cookie_block_item             | HONEY_COOKIE_BLOCK | HONEY_COOKIE_BLOCK |
| pumpkin_cookie_block_item           | PUMPKIN_COOKIE_BLOCK | PUMPKIN_COOKIE_BLOCK |
| sugar_cookie_block_item             | SUGAR_COOKIE_BLOCK | SUGAR_COOKIE_BLOCK |
| sweet_berry_cookie_block_item       | SWEET_BERRY_COOKIE_BLOCK | SWEET_BERRY_COOKIE_BLOCK |
| gingerbread_block_white             | GINGERBREAD_BLOCK_WHITE | GINGERBREAD_BLOCK_WHITE |
| gingerbread_block_light_gray        | GINGERBREAD_BLOCK_LIGHT_GRAY | GINGERBREAD_BLOCK_LIGHT_GRAY |
| gingerbread_block_gray              | GINGERBREAD_BLOCK_GRAY | GINGERBREAD_BLOCK_GRAY |
| gingerbread_block_black             | GINGERBREAD_BLOCK_BLACK | GINGERBREAD_BLOCK_BLACK |
| gingerbread_block_brown             | GINGERBREAD_BLOCK_BROWN | GINGERBREAD_BLOCK_BROWN |
| gingerbread_block_red               | GINGERBREAD_BLOCK_RED | GINGERBREAD_BLOCK_RED |
| gingerbread_block_orange            | GINGERBREAD_BLOCK_ORANGE | GINGERBREAD_BLOCK_ORANGE |
| gingerbread_block_yellow            | GINGERBREAD_BLOCK_YELLOW | GINGERBREAD_BLOCK_YELLOW |
| gingerbread_block_lime              | GINGERBREAD_BLOCK_LIME | GINGERBREAD_BLOCK_LIME |
| gingerbread_block_green             | GINGERBREAD_BLOCK_GREEN | GINGERBREAD_BLOCK_GREEN |
| gingerbread_block_cyan              | GINGERBREAD_BLOCK_CYAN | GINGERBREAD_BLOCK_CYAN |
| gingerbread_block_light_blue        | GINGERBREAD_BLOCK_LIGHT_BLUE | GINGERBREAD_BLOCK_LIGHT_BLUE |
| gingerbread_block_blue              | GINGERBREAD_BLOCK_BLUE | GINGERBREAD_BLOCK_BLUE |
| gingerbread_block_purple            | GINGERBREAD_BLOCK_PURPLE | GINGERBREAD_BLOCK_PURPLE |
| gingerbread_block_magenta           | GINGERBREAD_BLOCK_MAGENTA | GINGERBREAD_BLOCK_MAGENTA |
| gingerbread_block_pink              | GINGERBREAD_BLOCK_PINK | GINGERBREAD_BLOCK_PINK |
| candy_bowl_item                     | CANDY_BOWL_ITEM | CANDY_BOWL_ITEM |
| mint_jelly                          | MINT_JELLY | MINT_JELLY |
| mint_lamb                           | MINT_LAMB | MINT_LAMB |
| mint_lamb_feast                     | MINT_LAMB_FEAST | MINT_LAMB_FEAST |
| cinnamon_sapling                    | CINNAMON_SAPLING | CINNAMON_SAPLING |
| tap                                 | TAP | TAP |
| keg_item                            | KEG | KEG |
| fruit_log                           | FRUIT_LOG | FRUIT_LOG |
| fruit_wood                          | FRUIT_WOOD | FRUIT_WOOD |
| stripped_fruit_log                  | STRIPPED_FRUIT_LOG | STRIPPED_FRUIT_LOG |
| stripped_fruit_wood                 | STRIPPED_FRUIT_WOOD | STRIPPED_FRUIT_WOOD |
| fruit_door                          | FRUIT_DOOR | FRUIT_DOOR |
| fruit_planks                        | FRUIT_PLANKS | FRUIT_PLANKS |
| fruit_fence                         | FRUIT_FENCE | FRUIT_FENCE |
| fruit_fence_gate                    | FRUIT_FENCE_GATE | FRUIT_FENCE_GATE |
| fruit_stairs                        | FRUIT_STAIRS | FRUIT_STAIRS |
| fruit_trapdoor                      | FRUIT_TRAPDOOR | FRUIT_TRAPDOOR |
| fruit_cabinet                       | FRUIT_CABINET | FRUIT_CABINET |
| fruit_slab                          | FRUIT_SLAB | FRUIT_SLAB |
| fruit_button                        | FRUIT_BUTTON | FRUIT_BUTTON |
| fruit_pressure_plate                | FRUIT_PRESSURE_PLATE | FRUIT_PRESSURE_PLATE |
| hazelnut_leaves                     | HAZELNUT_LEAVES | HAZELNUT_LEAVES |
| marshmallow_block                   | MARSHMALLOW_BLOCK | MARSHMALLOW_BLOCK |
| golden_carrot_crate_block           | GOLDEN_CARROT_CRATE_BLOCK | GOLDEN_CARROT_CRATE_BLOCK |
| white_chocolate_box                 | WHITE_CHOCOLATE_BOX | WHITE_CHOCOLATE_BOX |
| orange_chocolate_box                | ORANGE_CHOCOLATE_BOX | ORANGE_CHOCOLATE_BOX |
| magenta_chocolate_box               | MAGENTA_CHOCOLATE_BOX | MAGENTA_CHOCOLATE_BOX |
| light_blue_chocolate_box            | LIGHT_BLUE_CHOCOLATE_BOX | LIGHT_BLUE_CHOCOLATE_BOX |
| yellow_chocolate_box                | YELLOW_CHOCOLATE_BOX | YELLOW_CHOCOLATE_BOX |
| lime_chocolate_box                  | LIME_CHOCOLATE_BOX | LIME_CHOCOLATE_BOX |
| pink_chocolate_box                  | PINK_CHOCOLATE_BOX | PINK_CHOCOLATE_BOX |
| gray_chocolate_box                  | GRAY_CHOCOLATE_BOX | GRAY_CHOCOLATE_BOX |
| light_gray_chocolate_box            | LIGHT_GRAY_CHOCOLATE_BOX | LIGHT_GRAY_CHOCOLATE_BOX |
| cyan_chocolate_box                  | CYAN_CHOCOLATE_BOX | CYAN_CHOCOLATE_BOX |
| blue_chocolate_box                  | BLUE_CHOCOLATE_BOX | BLUE_CHOCOLATE_BOX |
| brown_chocolate_box                 | BROWN_CHOCOLATE_BOX | BROWN_CHOCOLATE_BOX |
| green_chocolate_box                 | GREEN_CHOCOLATE_BOX | GREEN_CHOCOLATE_BOX |
| red_chocolate_box                   | RED_CHOCOLATE_BOX | RED_CHOCOLATE_BOX |
| black_chocolate_box                 | BLACK_CHOCOLATE_BOX | BLACK_CHOCOLATE_BOX |
| purple_chocolate_box                | PURPLE_CHOCOLATE_BOX | PURPLE_CHOCOLATE_BOX |
| milk_chocolate_block                | MILK_CHOCOLATE_BLOCK | MILK_CHOCOLATE_BLOCK |
| milk_chocolate_fence                | MILK_CHOCOLATE_FENCE | MILK_CHOCOLATE_FENCE |
| milk_chocolate_fence_gate           | MILK_CHOCOLATE_FENCE_GATE | MILK_CHOCOLATE_FENCE_GATE |
| milk_chocolate_stairs               | MILK_CHOCOLATE_STAIRS | MILK_CHOCOLATE_STAIRS |
| milk_chocolate_door                 | MILK_CHOCOLATE_DOOR | MILK_CHOCOLATE_DOOR |
| milk_chocolate_trapdoor             | MILK_CHOCOLATE_TRAPDOOR | MILK_CHOCOLATE_TRAPDOOR |
| milk_chocolate_slab                 | MILK_CHOCOLATE_SLAB | MILK_CHOCOLATE_SLAB |
| milk_chocolate_pillar               | MILK_CHOCOLATE_PILLAR | MILK_CHOCOLATE_PILLAR |
| dark_chocolate_block                | DARK_CHOCOLATE_BLOCK | DARK_CHOCOLATE_BLOCK |
| dark_chocolate_fence                | DARK_CHOCOLATE_FENCE | DARK_CHOCOLATE_FENCE |
| dark_chocolate_fence_gate           | DARK_CHOCOLATE_FENCE_GATE | DARK_CHOCOLATE_FENCE_GATE |
| dark_chocolate_stairs               | DARK_CHOCOLATE_STAIRS | DARK_CHOCOLATE_STAIRS |
| dark_chocolate_door                 | DARK_CHOCOLATE_DOOR | DARK_CHOCOLATE_DOOR |
| dark_chocolate_trapdoor             | DARK_CHOCOLATE_TRAPDOOR | DARK_CHOCOLATE_TRAPDOOR |
| dark_chocolate_slab                 | DARK_CHOCOLATE_SLAB | DARK_CHOCOLATE_SLAB |
| dark_chocolate_pillar               | DARK_CHOCOLATE_PILLAR | DARK_CHOCOLATE_PILLAR |
| white_chocolate_block               | WHITE_CHOCOLATE_BLOCK | WHITE_CHOCOLATE_BLOCK |
| white_chocolate_fence               | WHITE_CHOCOLATE_FENCE | WHITE_CHOCOLATE_FENCE |
| white_chocolate_fence_gate          | WHITE_CHOCOLATE_FENCE_GATE | WHITE_CHOCOLATE_FENCE_GATE |
| white_chocolate_stairs              | WHITE_CHOCOLATE_STAIRS | WHITE_CHOCOLATE_STAIRS |
| white_chocolate_door                | WHITE_CHOCOLATE_DOOR | WHITE_CHOCOLATE_DOOR |
| white_chocolate_trapdoor            | WHITE_CHOCOLATE_TRAPDOOR | WHITE_CHOCOLATE_TRAPDOOR |
| white_chocolate_slab                | WHITE_CHOCOLATE_SLAB | WHITE_CHOCOLATE_SLAB |
| white_chocolate_pillar              | WHITE_CHOCOLATE_PILLAR | WHITE_CHOCOLATE_PILLAR |
| blood_chocolate_block               | BLOOD_CHOCOLATE_BLOCK | BLOOD_CHOCOLATE_BLOCK |
| blood_chocolate_fence               | BLOOD_CHOCOLATE_FENCE | BLOOD_CHOCOLATE_FENCE |
| blood_chocolate_fence_gate          | BLOOD_CHOCOLATE_FENCE_GATE | BLOOD_CHOCOLATE_FENCE_GATE |
| blood_chocolate_stairs              | BLOOD_CHOCOLATE_STAIRS | BLOOD_CHOCOLATE_STAIRS |
| blood_chocolate_door                | BLOOD_CHOCOLATE_DOOR | BLOOD_CHOCOLATE_DOOR |
| blood_chocolate_trapdoor            | BLOOD_CHOCOLATE_TRAPDOOR | BLOOD_CHOCOLATE_TRAPDOOR |
| blood_chocolate_slab                | BLOOD_CHOCOLATE_SLAB | BLOOD_CHOCOLATE_SLAB |
| blood_chocolate_pillar              | BLOOD_CHOCOLATE_PILLAR | BLOOD_CHOCOLATE_PILLAR |
| roasted_cocoa_beans                 | ROASTED_COCOA_BEANS | ROASTED_COCOA_BEANS |
| cocoa_solids                        | COCOA_SOLIDS | COCOA_SOLIDS |
| blood_chocolate_bar                 | BLOOD_CHOCOLATE_BAR | BLOOD_CHOCOLATE_BAR |
| blood_chocolate_chips               | BLOOD_CHOCOLATE_CHIPS | BLOOD_CHOCOLATE_CHIPS |
| blood_chocolate_filled_bar          | BLOOD_CHOCOLATE_FILLED_BAR | BLOOD_CHOCOLATE_FILLED_BAR |
| blood_chocolate_truffle             | BLOOD_CHOCOLATE_TRUFFLE | BLOOD_CHOCOLATE_TRUFFLE |
| xocolati                            | XOCOLATL | XOCOLATL |
| gourmet_hot_chocolate               | GOURMET_HOT_CHOCOLATE | GOURMET_HOT_CHOCOLATE |
| blood_chocolate_fondue_block        | BLOOD_CHOCOLATE_FONDUE_BLOCK | BLOOD_CHOCOLATE_FONDUE_BLOCK |
| blood_chocolate_dipped_sweet_berry  | BLOOD_CHOCOLATE_DIPPED_SWEET_BERRY | BLOOD_CHOCOLATE_DIPPED_SWEET_BERRY |
| blood_chocolate_dipped_glow_berry   | BLOOD_CHOCOLATE_DIPPED_GLOW_BERRY | BLOOD_CHOCOLATE_DIPPED_GLOW_BERRY |
| blood_chocolate_dipped_apple_slice  | BLOOD_CHOCOLATE_DIPPED_APPLE_SLICE | BLOOD_CHOCOLATE_DIPPED_APPLE_SLICE |
| blood_chocolate_dipped_marshmallow  | BLOOD_CHOCOLATE_DIPPED_MARSHMALLOW | BLOOD_CHOCOLATE_DIPPED_MARSHMALLOW |
| blood_chocolate_dipped_graham_cracker | BLOOD_CHOCOLATE_DIPPED_GRAHAM_CRACKER | BLOOD_CHOCOLATE_DIPPED_GRAHAM_CRACKER |
| blood_chocolate_dipped_bacon        | BLOOD_CHOCOLATE_DIPPED_BACON | BLOOD_CHOCOLATE_DIPPED_BACON |
| blood_chocolate_dipped_coffee_bean  | BLOOD_CHOCOLATE_DIPPED_COFFEE_BEAN | BLOOD_CHOCOLATE_DIPPED_COFFEE_BEAN |
| dark_chocolate_fondue_block         | DARK_CHOCOLATE_FONDUE_BLOCK | DARK_CHOCOLATE_FONDUE_BLOCK |
| dark_chocolate_dipped_sweet_berry   | DARK_CHOCOLATE_DIPPED_SWEET_BERRY | DARK_CHOCOLATE_DIPPED_SWEET_BERRY |
| dark_chocolate_dipped_glow_berry    | DARK_CHOCOLATE_DIPPED_GLOW_BERRY | DARK_CHOCOLATE_DIPPED_GLOW_BERRY |
| dark_chocolate_dipped_apple_slice   | DARK_CHOCOLATE_DIPPED_APPLE_SLICE | DARK_CHOCOLATE_DIPPED_APPLE_SLICE |
| dark_chocolate_dipped_marshmallow   | DARK_CHOCOLATE_DIPPED_MARSHMALLOW | DARK_CHOCOLATE_DIPPED_MARSHMALLOW |
| dark_chocolate_dipped_graham_cracker | DARK_CHOCOLATE_DIPPED_GRAHAM_CRACKER | DARK_CHOCOLATE_DIPPED_GRAHAM_CRACKER |
| dark_chocolate_dipped_bacon         | DARK_CHOCOLATE_DIPPED_BACON | DARK_CHOCOLATE_DIPPED_BACON |
| dark_chocolate_dipped_coffee_bean   | DARK_CHOCOLATE_DIPPED_COFFEE_BEAN | DARK_CHOCOLATE_DIPPED_COFFEE_BEAN |
| peanuts_in_shell                    | PEANUTS_IN_SHELL | PEANUTS_IN_SHELL |
| peanuts                             | PEANUTS | PEANUTS |
| roasted_peanuts                     | ROASTED_PEANUTS | ROASTED_PEANUTS |
| hazelnuts_in_shell                  | HAZELNUTS_IN_SHELL | HAZELNUTS_IN_SHELL |
| hazelnuts                           | HAZELNUTS | HAZELNUTS |
| roasted_hazelnuts                   | ROASTED_HAZELNUTS | ROASTED_HAZELNUTS |
| wild_peanut_block                   | WILD_PEANUT_BLOCK | WILD_PEANUT_BLOCK |
| wild_chili_block                    | WILD_CHILI_BLOCK | WILD_CHILI_BLOCK |
| wild_mallow_root_block              | WILD_MALLOW_ROOT_BLOCK | WILD_MALLOW_ROOT_BLOCK |
| mallow_root                         | MALLOW_ROOT | MALLOW_ROOT |
| peanut_butter_jelly                 | PEANUT_BUTTER_JELLY | PEANUT_BUTTER_JELLY |
| hazelnut_sapling                    | HAZELNUT_SAPLING | HAZELNUT_SAPLING |
| jar                                 | JAR | JAR |
| ice_cream_sundae                    | ICE_CREAM_SUNDAE | ICE_CREAM_SUNDAE |
| mallow_powder                       | MALLOW_POWDER | MALLOW_POWDER |
| ground_coffee                       | GROUND_COFFEE | GROUND_COFFEE |
| blood_chocolate_syrup_fluid_bucket  | BLOOD_CHOCOLATE_SYRUP_FLUID_BUCKET | BLOOD_CHOCOLATE_SYRUP_FLUID_BUCKET |
| blood_chocolate_syrup_bottle        | BLOOD_CHOCOLATE_SYRUP_BOTTLE | BLOOD_CHOCOLATE_SYRUP_BOTTLE |

## Lance active, no same registry id in vomiter

| registry_id | lance_field | note |
| --- | --- | --- |
| ground_cinnamon_block | GROUND_CINNAMON_BLOCK_ITEM | vomiter has ground_cinnamon_sack commented |

## Vomiter active, not in lance 2.1.1

| registry_id | vomiter_field |
| --- | --- |
| vat | VAT |
| lid | LID |
| salt | SALT |
| salt_block_item | SALT_BLOCK_ITEM |
| evaporator | EVAPORATOR |
| juicer | JUICER |
| egg_yolk | EGG_YOLK |
| egg_white | EGG_WHITE |
| egg_white_fluid_bucket | EGG_WHITE_FLUID_BUCKET |
| stiff_peaks | STIFF_PEAKS |
| chili_crate | CHILI_CRATE |
| chili_powder_sack | CHILI_POWDER_SACK |
| sliced_chili | SLICED_CHILI |
| wild_garlic_block | WILD_GARLIC_BLOCK |
| garlic | GARLIC |
| garlic_crate | GARLIC_CRATE |
| garlic_clove | GARLIC_CLOVE |
| grated_garlic | GRATED_GARLIC |
| roasted_garlic | ROASTED_GARLIC |
| garlic_bread | GARLIC_BREAD |
| cheesy_garlic_bread | CHEESY_GARLIC_BREAD |

## Vomiter commented, not in lance 2.1.1

| registry_id | vomiter_field |
| --- | --- |
| whisk | WHISK |
| fruit_bowl | FRUIT_BOWL |
| corn_sack | CORN_SACK |
| ground_cinnamon_sack | GROUND_CINNAMON_BLOCK_ITEM |
| apple_leaves | APPLE_LEAVES |
| apple_sapling | APPLE_SAPLING |
| rombosse | ROMBOSSE |
| apple_slaw | APPLE_SLAW |
| pork_and_apples_feast | PORK_AND_APPLES_FEAST |
| pork_and_apples | PORK_AND_APPLES |
| apple_chips | APPLE_CHIPS |
| stuffed_apples_feast | STUFFED_APPLES_FEAST |
| stuffed_apple | STUFFED_APPLE |
| stuffed_apple_ice_cream | STUFFED_APPLE_ICE_CREAM |
| mulligatawny_soup | MULLIGATAWNY_SOUP |
| tarte_tatin_in_pan | TARTE_TATIN_IN_PAN |
| tarte_tatin | TARTE_TATIN |
| tarte_tatin_slice | TARTE_TATIN_SLICE |
| aebleflaesk | AEBLEFLAESK |
| candy_bar_salad | CANDY_BAR_SALAD |
| hanging_onion | HANGING_ONION |
| hanging_mint | HANGING_MINT |
| hanging_ham | HANGING_HAM |
| hanging_chili | HANGING_CHILI |
| hanging_dried_chili | HANGING_DRIED_CHILI |
| hanging_corn | HANGING_CORN |
| jalapeno_stuffed_potato | JALAPENO_STUFFED_POTATO |
| jalapeno_popper | JALAPENO_POPPER |
| chili_cheese_cornbread_muffin | CHILI_CHEESE_CORNBREAD_MUFFIN |
| chili_con_carne_feast | CHILI_CON_CARNE_FEAST |
| chili_con_carne | CHILI_CON_CARNE |
| white_chili_feast | WHITE_CHILI_FEAST |
| white_chili | WHITE_CHILI |
| peanut_in_shell_sack | PEANUT_IN_SHELL_SACK |
| peanut_sack | PEANUT_SACK |
| roasted_peanut_sack | ROASTED_PEANUT_SACK |
| nut_butter_cookie | NUT_BUTTER_COOKIE |
| nut_butter_cookie_dough | NUT_BUTTER_COOKIE_DOUGH |
| nut_butter_ice_cream | NUT_BUTTER_ICE_CREAM |
| nut_butter_milkshake | NUT_BUTTER_MILKSHAKE |
| nut_butter_custard | NUT_BUTTER_CUSTARD |
| nut_butter_cookie_block | NUT_BUTTER_COOKIE_BLOCK |
| hazelnut_in_shell_sack | HAZELNUT_IN_SHELL_SACK |
| hazelnut_sack | HAZELNUT_SACK |
| roasted_hazelnut_sack | ROASTED_HAZELNUT_SACK |
| mississippi_mud_pie | MISSISSIPPI_MUD_PIE |
| mississippi_mud_pie_slice | MISSISSIPPI_MUD_PIE_SLICE |
| hazelnut_soup | HAZELNUT_SOUP |
| mallow_root_crate | MALLOW_ROOT_CRATE |
| mallow_powder_sack | MALLOW_POWDER_SACK |
| grasshopper_pie | GRASSHOPPER_PIE |
| marshmallow_slice_feast | MARSHMALLOW_SLICE_FEAST |
| grasshopper_pie_slice | GRASSHOPPER_PIE_SLICE |
| marshmallow_slice | MARSHMALLOW_SLICE |
| rocky_road | ROCKY_ROAD |
| coffee_cherry_crate | COFFEE_CHERRY_CRATE |
| green_coffee_bean_sack | GREEN_COFFEE_BEAN_SACK |
| coffee_bean_sack | COFFEE_BEAN_SACK |
| affogato | AFFOGATO |
| cocoa_bean_sack | COCOA_BEAN_SACK |
| roasted_cocoa_bean_sack | ROASTED_COCOA_BEAN_SACK |
| ground_coffee_sack | GROUND_COFFEE_SACK |
| cocoa_powder_sack | COCOA_POWDER_SACK |
| cocoa_solids_sack | COCOA_SOLIDS_SACK |
| chocolate_cookie_block | CHOCOLATE_COOKIE_BLOCK |
| cocoa_powder | COCOA_POWDER |
| chocolate_cookie_dough | CHOCOLATE_COOKIE_DOUGH |
| chocolate_cookie | CHOCOLATE_COOKIE |
| potato_salad | POTATO_SALAD |
| onion_soup | ONION_SOUP |
| bacon_egg_pie | BACON_EGG_PIE |
| bacon_egg_pie_slice | BACON_EGG_PIE_SLICE |
| onion_bhaji | ONION_BHAJI |
| fat_potatoes | FAT_POTATOES |
| cinnamon_toast | CINNAMON_TOAST |
| panforte | PANFORTE |
| panforte_slice | PANFORTE_SLICE |
| currywurst | CURRYWURST |
| borscht | BORSCHT |
| pamonha | PAMONHA |
| bruschetta_feast | BRUSCHETTA_FEAST |
| bruschetta | BRUSCHETTA |
| aioli_jar_item | AIOLI |
| chicken_kiev | CHICKEN_KIEV |
| devilled_sausages | DEVILLED_SAUSAGES |
| hanging_garlic | HANGING_GARLIC |
| aglio_e_olio | AGLIO_E_OLIO |
| penne | PENNE |
| penne_all_arrabbiata | PENNE_ALL_ARRABIATA |
| dynamic_toast | DYNAMIC_TOAST |
| dynamic_jam | DYNAMIC_JAM |
| hazelnut_petal_litter_item | HAZELNUT_PETAL_LITTER_ITEM |
| apple_petal_litter_item | APPLE_PETAL_LITTER_ITEM |