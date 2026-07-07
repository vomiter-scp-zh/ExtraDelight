package com.vomiter.extradelight.registry;

import java.util.function.Supplier;


import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetMenu;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayMenu;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlockMenu;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackMenu;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathMenu;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkCabinetMenu;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketMenu;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerMenu;
import com.vomiter.extradelight.common.complex.workstations.doughshaping.DoughShapingMenu;
import com.vomiter.extradelight.common.complex.workstations.meltingpot.MeltingPotMenu;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlMenu;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenMenu;
import com.vomiter.extradelight.common.complex.workstations.vat.VatMenu;
import com.vomiter.extradelight.gui.StyleableMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraDelightContainers {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU,
			ExtraDelight.MOD_ID);

	public static final Supplier<MenuType<OvenMenu>> OVEN_MENU = MENU_TYPES.register("oven",
			() -> IForgeMenuType.create(OvenMenu::new));
    public static final Supplier<MenuType<MixingBowlMenu>> MIXING_BOWL_MENU = MENU_TYPES.register("mixing_bowl",
            () -> IForgeMenuType.create(MixingBowlMenu::new));
    public static final Supplier<MenuType<DoughShapingMenu>> DOUGH_SHAPING_MENU = MENU_TYPES.register("dough_shaping",
            () -> IForgeMenuType.create(DoughShapingMenu::new));
    public static final Supplier<MenuType<MeltingPotMenu>> MELTING_POT_MENU = MENU_TYPES.register("melting_pot",
            () -> IForgeMenuType.create(MeltingPotMenu::new));
    public static final Supplier<MenuType<ChillerMenu>> CHILLER_MENU = MENU_TYPES.register("chiller",
            () -> IForgeMenuType.create(ChillerMenu::new));
    public static final Supplier<MenuType<VatMenu>> VAT_MENU = MENU_TYPES.register("vat",
            () -> IForgeMenuType.create(VatMenu::new));
    public static final Supplier<MenuType<StyleableMenu>> STYLE_MENU = MENU_TYPES.register("style",
            () -> IForgeMenuType.create(StyleableMenu::new));
    public static final Supplier<MenuType<SinkCabinetMenu>> SINK_MENU = MENU_TYPES.register("sink",
            () -> IForgeMenuType.create(SinkCabinetMenu::new));
    public static final Supplier<MenuType<CounterCabinetMenu>> COUNTER_CABINET_MENU = MENU_TYPES
            .register("counter_cabinet", () -> IForgeMenuType.create(CounterCabinetMenu::new));
    public static final Supplier<MenuType<FoodDisplayMenu>> FOOD_DISPLAY_MENU = MENU_TYPES.register("food_display",
            () -> IForgeMenuType.create(FoodDisplayMenu::new));
    public static final Supplier<MenuType<KnifeBlockMenu>> KNIFE_BLOCK_MENU = MENU_TYPES.register("knife_block",
            () -> IForgeMenuType.create(KnifeBlockMenu::new));
    public static final Supplier<MenuType<SpiceRackMenu>> SPICE_RACK_MENU = MENU_TYPES.register("spice_rack",
            () -> IForgeMenuType.create(SpiceRackMenu::new));

    public static final Supplier<MenuType<WreathMenu>> WREATH_MENU = MENU_TYPES.register("wreath",
            () -> IForgeMenuType.create(WreathMenu::new));
    public static final Supplier<MenuType<PicnicBasketMenu>> PICNIC_BASKET_MENU = MENU_TYPES.register("picnic_basket",
            () -> IForgeMenuType.create(PicnicBasketMenu::new));






    /*

	public static void register(IEventBus modBus) {
		MENU_TYPES.register(modBus);
	}

     */

}