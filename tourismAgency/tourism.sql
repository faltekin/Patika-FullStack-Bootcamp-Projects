PGDMP     	    .                |            tourism    15.5    15.5 "    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    16847    tourism    DATABASE     |   CREATE DATABASE tourism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE tourism;
                postgres    false            �            1259    16849    hotel    TABLE     �  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    mail character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16848    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    16857    hotel_pansion    TABLE     �   CREATE TABLE public.hotel_pansion (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pansion_type character varying NOT NULL
);
 !   DROP TABLE public.hotel_pansion;
       public         heap    postgres    false            �            1259    16856    hotel_pansion_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pansion ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_pansion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16908    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    16913    hotel_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    16871    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_name character varying NOT NULL,
    guest_citizen_id character varying NOT NULL,
    guest_mail character varying NOT NULL,
    guest_phone character varying NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16870    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16887    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pansion_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16886    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    16879    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    usertype character varying NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16878    user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221                      0    16849    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    215   �(                 0    16857    hotel_pansion 
   TABLE DATA           C   COPY public.hotel_pansion (id, hotel_id, pansion_type) FROM stdin;
    public          postgres    false    217   �)       "          0    16908    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    224   f*                 0    16871    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    219    +       !          0    16887    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pansion_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    223   �-                 0    16879    user 
   TABLE DATA           B   COPY public."user" (id, username, password, usertype) FROM stdin;
    public          postgres    false    221   �.       *           0    0    hotel_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.hotel_id_seq', 8, true);
          public          postgres    false    214            +           0    0    hotel_pansion_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hotel_pansion_id_seq', 22, true);
          public          postgres    false    216            ,           0    0    hotel_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotel_season_id_seq', 18, true);
          public          postgres    false    225            -           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 13, true);
          public          postgres    false    218            .           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 13, true);
          public          postgres    false    222            /           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 6, true);
          public          postgres    false    220            �           2606    16863     hotel_pansion hotel_pansion_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pansion
    ADD CONSTRAINT hotel_pansion_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pansion DROP CONSTRAINT hotel_pansion_pkey;
       public            postgres    false    217                       2606    16855    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            �           2606    16912    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    224            �           2606    16877    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219            �           2606    16893    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    223            �           2606    16885    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    221               �   x�Uнn� ����=T����R�N�R�˕�[�	�����;���G	��H|:�D?ﭛ.x�>L���瑧��ջN��K�1�"����͇͆�[ۙ:^�ƛ��{��pFz5ڠ�N#L��m痘�T�V7f�VWm����Y�����
��%�kfv��&zx��~}l������Iպ�ܖ��b��/v���!��H*I���w���Q���j�         �   x�}�M
�0�דS�����D
��fh�bz��{�^Nw6-n_��� p���Qu5ͥ��mNrCŝl�n�m�H���<t#�T+��ac֘#Sr���M�]��b嚜y=��f�̜�M-�r�i�RM��)�h4"?�J��k}s7ɳ��c8؊W�7`̢�%C�[���)D���^k� ���D��%��      "   �   x�u�A�0��T,�v����uM�ۄJ>,`@F**�`�#�YI��N��zq���w��ݚ;	�4K]D��w��7x��ā�J��(�4��*�yfY��U�&���FK��V�����z�ک���_f~ĞPh         �  x���o�0ǟ����)�;6��ct�����ꦎ�8�[� ��h��9?�t����"$˺3����� � C�Oax
Qg"ܘ,W�����%֣������\�+%���Gx� ���5�~.��F�o�r���R��ٛ˷ӓ�U"�� t�K�\e���~����o��(M"�>��=���dU�&�'�e�*k��������i.�*���/�Zuq�e�J��4F&�#�e��&�j�?��Q�;�=��D�Y�-�6�U�E.�&����>q�2�x<�e�l������dJ����l���"S5��,�aT�ts�����0�L��R�fq�ׅ�\X�u��l��p�ei�c����7��>N��M?���B��,kv�"/ �)
&�x�JfAL�L�Qɇ�d++;P3[4�`/əA�I&ߺ�I0�Ry���ȹp�M���f'f�0�(��y����8���cw>$9o;��] ������Lv�&�ə�K��y�&�s��(�Ñte�y2l�屽��fߟoʇz%~����l�N-��{�AڷI�9�-���������8���F$Ɵ ؽ�-!�BPZh@8e�s�A��=$��Q>fc�v�pK�t���F��.�aI�um���D�A;�N_������������@�f&�Xz:U2�0��}�y���H�      !     x�e��m�0E����0I��Wh��1�R�@���1:Lн�I%�b�dX���LLJB�����{=ׅ
�0cqhA���e����{��?���(7V��:\)oq�$>�:�?��x�⺳3ҌA�� ^�aF��ݸ�:S�W���kj*��qRX��]��hG��BS����;���ae�Eg	�k?���]��S`�q��I ��'��.s\V����V�5����pЇm�g���GW%�}m�pxJ)�����         M   x�3�,��/�442�tt����2�L+M,�415�t���tu�2�LL����43�4�*3�LMI�46526E������ CrN     