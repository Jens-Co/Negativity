package com.elikill58.negativity.api.packets.nms.versions;

import com.elikill58.negativity.api.item.ItemRegistrar;
import com.elikill58.negativity.api.item.Material;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public class PreFlattening {

	private static final Int2ObjectMap<String> nameById = new Int2ObjectArrayMap<>();

	static {
		nameById.put(0, "air");
		nameById.put(1, "stone");
		nameById.put(2, "grass");
		nameById.put(3, "dirt");
		nameById.put(4, "cobblestone");
		nameById.put(5, "planks");
		nameById.put(6, "sapling");
		nameById.put(7, "bedrock");
		nameById.put(8, "flowing_water");
		nameById.put(9, "water");
		nameById.put(10, "flowing_lava");
		nameById.put(11, "lava");
		nameById.put(12, "sand");
		nameById.put(13, "gravel");
		nameById.put(14, "gold_ore");
		nameById.put(15, "iron_ore");
		nameById.put(16, "coal_ore");
		nameById.put(17, "log");
		nameById.put(18, "leaves");
		nameById.put(19, "sponge");
		nameById.put(20, "glass");
		nameById.put(21, "lapis_ore");
		nameById.put(22, "lapis_block");
		nameById.put(23, "dispenser");
		nameById.put(24, "sandstone");
		nameById.put(25, "noteblock");
		nameById.put(26, "bed");
		nameById.put(27, "golden_rail");
		nameById.put(28, "detector_rail");
		nameById.put(29, "sticky_piston");
		nameById.put(30, "web");
		nameById.put(31, "tallgrass");
		nameById.put(32, "deadbush");
		nameById.put(33, "piston");
		nameById.put(34, "piston_head");
		nameById.put(35, "wool");
		nameById.put(36, "piston_extension");
		nameById.put(37, "yellow_flower");
		nameById.put(38, "red_flower");
		nameById.put(39, "brown_mushroom");
		nameById.put(40, "red_mushroom");
		nameById.put(41, "gold_block");
		nameById.put(42, "iron_block");
		nameById.put(43, "double_stone_slab");
		nameById.put(44, "stone_slab");
		nameById.put(45, "brick_block");
		nameById.put(46, "tnt");
		nameById.put(47, "bookshelf");
		nameById.put(48, "mossy_cobblestone");
		nameById.put(49, "obsidian");
		nameById.put(50, "torch");
		nameById.put(51, "fire");
		nameById.put(52, "mob_spawner");
		nameById.put(53, "oak_stairs");
		nameById.put(54, "chest");
		nameById.put(55, "redstone_wire");
		nameById.put(56, "diamond_ore");
		nameById.put(57, "diamond_block");
		nameById.put(58, "crafting_table");
		nameById.put(59, "wheat");
		nameById.put(60, "farmland");
		nameById.put(61, "furnace");
		nameById.put(62, "lit_furnace");
		nameById.put(63, "standing_sign");
		nameById.put(64, "wooden_door");
		nameById.put(65, "ladder");
		nameById.put(66, "rail");
		nameById.put(67, "stone_stairs");
		nameById.put(68, "wall_sign");
		nameById.put(69, "lever");
		nameById.put(70, "stone_pressure_plate");
		nameById.put(71, "iron_door");
		nameById.put(72, "wooden_pressure_plate");
		nameById.put(73, "redstone_ore");
		nameById.put(74, "lit_redstone_ore");
		nameById.put(75, "unlit_redstone_torch");
		nameById.put(76, "redstone_torch");
		nameById.put(77, "stone_button");
		nameById.put(78, "snow_layer");
		nameById.put(79, "ice");
		nameById.put(80, "snow");
		nameById.put(81, "cactus");
		nameById.put(82, "clay");
		nameById.put(83, "reeds");
		nameById.put(84, "jukebox");
		nameById.put(85, "fence");
		nameById.put(86, "pumpkin");
		nameById.put(87, "netherrack");
		nameById.put(88, "soul_sand");
		nameById.put(89, "glowstone");
		nameById.put(90, "portal");
		nameById.put(91, "lit_pumpkin");
		nameById.put(92, "cake");
		nameById.put(93, "unpowered_repeater");
		nameById.put(94, "powered_repeater");
		nameById.put(95, "stained_glass");
		nameById.put(96, "trapdoor");
		nameById.put(97, "monster_egg");
		nameById.put(98, "stonebrick");
		nameById.put(99, "brown_mushroom_block");
		nameById.put(100, "red_mushroom_block");
		nameById.put(101, "iron_bars");
		nameById.put(102, "glass_pane");
		nameById.put(103, "melon_block");
		nameById.put(104, "pumpkin_stem");
		nameById.put(105, "melon_stem");
		nameById.put(106, "vine");
		nameById.put(107, "fence_gate");
		nameById.put(108, "brick_stairs");
		nameById.put(109, "stone_brick_stairs");
		nameById.put(110, "mycelium");
		nameById.put(111, "waterlily");
		nameById.put(112, "nether_brick");
		nameById.put(113, "nether_brick_fence");
		nameById.put(114, "nether_brick_stairs");
		nameById.put(115, "nether_wart");
		nameById.put(116, "enchanting_table");
		nameById.put(117, "brewing_stand");
		nameById.put(118, "cauldron");
		nameById.put(119, "end_portal");
		nameById.put(120, "end_portal_frame");
		nameById.put(121, "end_stone");
		nameById.put(122, "dragon_egg");
		nameById.put(123, "redstone_lamp");
		nameById.put(124, "lit_redstone_lamp");
		nameById.put(125, "double_wooden_slab");
		nameById.put(126, "wooden_slab");
		nameById.put(127, "cocoa");
		nameById.put(128, "sandstone_stairs");
		nameById.put(129, "emerald_ore");
		nameById.put(130, "ender_chest");
		nameById.put(131, "tripwire_hook");
		nameById.put(132, "tripwire");
		nameById.put(133, "emerald_block");
		nameById.put(134, "spruce_stairs");
		nameById.put(135, "birch_stairs");
		nameById.put(136, "jungle_stairs");
		nameById.put(137, "command_block");
		nameById.put(138, "beacon");
		nameById.put(139, "cobblestone_wall");
		nameById.put(140, "flower_pot");
		nameById.put(141, "carrots");
		nameById.put(142, "potatoes");
		nameById.put(143, "wooden_button");
		nameById.put(144, "skull");
		nameById.put(145, "anvil");
		nameById.put(146, "trapped_chest");
		nameById.put(147, "light_weighted_pressure_plate");
		nameById.put(148, "heavy_weighted_pressure_plate");
		nameById.put(149, "unpowered_comparator");
		nameById.put(150, "powered_comparator");
		nameById.put(151, "daylight_detector");
		nameById.put(152, "redstone_block");
		nameById.put(153, "quartz_ore");
		nameById.put(154, "hopper");
		nameById.put(155, "quartz_block");
		nameById.put(156, "quartz_stairs");
		nameById.put(157, "activator_rail");
		nameById.put(158, "dropper");
		nameById.put(159, "stained_hardened_clay");
		nameById.put(160, "stained_glass_pane");
		nameById.put(161, "leaves2");
		nameById.put(162, "log2");
		nameById.put(163, "acacia_stairs");
		nameById.put(164, "dark_oak_stairs");
		nameById.put(165, "slime");
		nameById.put(166, "barrier");
		nameById.put(167, "iron_trapdoor");
		nameById.put(168, "prismarine");
		nameById.put(169, "sea_lantern");
		nameById.put(170, "hay_block");
		nameById.put(171, "carpet");
		nameById.put(172, "hardened_clay");
		nameById.put(173, "coal_block");
		nameById.put(174, "packed_ice");
		nameById.put(175, "double_plant");
		nameById.put(176, "standing_banner");
		nameById.put(177, "wall_banner");
		nameById.put(178, "daylight_detector_inverted");
		nameById.put(179, "red_sandstone");
		nameById.put(180, "red_sandstone_stairs");
		nameById.put(181, "double_stone_slab2");
		nameById.put(182, "stone_slab2");
		nameById.put(183, "spruce_fence_gate");
		nameById.put(184, "birch_fence_gate");
		nameById.put(185, "jungle_fence_gate");
		nameById.put(186, "dark_oak_fence_gate");
		nameById.put(187, "acacia_fence_gate");
		nameById.put(188, "spruce_fence");
		nameById.put(189, "birch_fence");
		nameById.put(190, "jungle_fence");
		nameById.put(191, "dark_oak_fence");
		nameById.put(192, "acacia_fence");
		nameById.put(193, "spruce_door");
		nameById.put(194, "birch_door");
		nameById.put(195, "jungle_door");
		nameById.put(196, "acacia_door");
		nameById.put(197, "dark_oak_door");
		nameById.put(198, "end_rod");
		nameById.put(199, "chorus_plant");
		nameById.put(200, "chorus_flower");
		nameById.put(201, "purpur_block");
		nameById.put(202, "purpur_pillar");
		nameById.put(203, "purpur_stairs");
		nameById.put(204, "purpur_double_slab");
		nameById.put(205, "purpur_slab");
		nameById.put(206, "end_bricks");
		nameById.put(207, "beetroots");
		nameById.put(208, "grass_path");
		nameById.put(209, "end_gateway");
		nameById.put(210, "repeating_command_block");
		nameById.put(211, "chain_command_block");
		nameById.put(212, "frosted_ice");
		nameById.put(213, "magma");
		nameById.put(214, "nether_wart_block");
		nameById.put(215, "red_nether_brick	");
		nameById.put(216, "bone_block");
		nameById.put(217, "structure_void");
		nameById.put(218, "observer");
		nameById.put(219, "white_shulker_box");
		nameById.put(220, "orange_shulker_box");
		nameById.put(221, "magenta_shulker_box");
		nameById.put(222, "light_blue_shulker_box");
		nameById.put(223, "yellow_shulker_box");
		nameById.put(224, "lime_shulker_box");
		nameById.put(225, "pink_shulker_box");
		nameById.put(226, "gray_shulker_box");
		nameById.put(227, "silver_shulker_box");
		nameById.put(228, "cyan_shulker_box");
		nameById.put(229, "purple_shulker_box");
		nameById.put(230, "blue_shulker_box");
		nameById.put(231, "brown_shulker_box");
		nameById.put(232, "green_shulker_box");
		nameById.put(233, "red_shulker_box");
		nameById.put(234, "black_shulker_box");
		nameById.put(235, "white_glazed_terracotta");
		nameById.put(236, "orange_glazed_terracotta");
		nameById.put(237, "magenta_glazed_terracotta");
		nameById.put(238, "light_blue_glazed_terracotta");
		nameById.put(239, "yellow_glazed_terracotta");
		nameById.put(240, "lime_glazed_terracotta");
		nameById.put(241, "pink_glazed_terracotta");
		nameById.put(242, "gray_glazed_terracotta");
		nameById.put(243, "silver_glazed_terracotta");
		nameById.put(244, "cyan_glazed_terracotta");
		nameById.put(245, "purple_glazed_terracotta");
		nameById.put(246, "blue_glazed_terracotta");
		nameById.put(247, "brown_glazed_terracotta");
		nameById.put(248, "green_glazed_terracotta");
		nameById.put(249, "red_glazed_terracotta");
		nameById.put(250, "black_glazed_terracotta");
		nameById.put(251, "concrete");
		nameById.put(252, "concrete_powder");
		// 253 & 254 are unused
		nameById.put(253, "air");
		nameById.put(254, "air");
		nameById.put(255, "structure_block");
	}

	public static Material get(int id) {
		// should we check for all blocks as described here:
		// https://minecraft.fandom.com/wiki/Java_Edition_data_values/Pre-flattening#Block_IDs
		// ?
		// be sure there is block
		return ItemRegistrar.getInstance().get(nameById.getOrDefault(id, "air"), "stone");
	}
	
	public static int size() {
		return nameById.size();
	}
}
