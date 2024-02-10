PGDMP     &        
    
        |            tourism    15.5    15.5 "    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public          postgres    false    217   e*       "          0    16908    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    224   �+                 0    16871    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    219   �,       !          0    16887    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pansion_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    223   �0                 0    16879    user 
   TABLE DATA           B   COPY public."user" (id, username, password, usertype) FROM stdin;
    public          postgres    false    221   �3       *           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 18, true);
          public          postgres    false    214            +           0    0    hotel_pansion_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hotel_pansion_id_seq', 46, true);
          public          postgres    false    216            ,           0    0    hotel_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotel_season_id_seq', 36, true);
          public          postgres    false    225            -           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 48, true);
          public          postgres    false    218            .           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 65, true);
          public          postgres    false    222            /           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 7, true);
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
       public            postgres    false    221               �  x�m�Mn�0���Sd�E!rH���	� h�v6�ab*�WPr�V�C�!E�rS2� }z3o�ö9ؾ>�w�ƺ3�۷��-(���Y���L�T�LL�~8�6�c�͚-�
�T³���d�����
6���05��O�O�E�B-���6���,��[۾1�@�Eĸ�\P�3v�\A�Ό�t���u�F"b�@��e�f��N{{z7=l�709���u+a7�l;3�8zN�V)���!V�g�7����}��u�t�C&3���4fɲ�"�(T�� S"\������nM�(��e~6Gxt���эN4��4�庳�"��մ�K�5^�Y_&v=h�`g��gK�t�T�F�%-$]1�i}�#|�c�m;����+Jf4����h]$�/�3/�!���Υ��[÷aX�m��2���z==��c�?�1 �         .  x�}�KJA��է��T��D.�E�M�Ȑ6�qr��s㽬��5�ۯ�z�U����^����������>(ϗ��`�9���0�|'}C�1+�PzY,0h�F.���r��M����c�k���/}����NC?�j�����L{�`�4"�!�p�:�Xi���^1�H�F�B�������A"������(E�9X	♈����o"��>MZ\o���]���@�Q�������J�$2}a��lX�|��	����]���w%"�
uSg
���Wl�u%"�Kmu(���\)�~>|.�      "   �   x�u�K�0��.�x��ܥ�?G�IL�)�р��������Y6-��$O�M9H�zs`e��dtk�,H�q'r��~�V�v��p��%�^��d�����+�L�����b=�Y��">��@�3����Q�3]e�N�����,:Y��L@�
!����!L{��Ъc��;�HD�#G-uA�s��+/�	5`������?(�����4{�gO��"�9�{�wJ�Ho�         �  x��W�n�6]S_�E�ʀoj�v��	;FR�^pF�1k=�:��^RQ�q����z�����%M��bJ_a�
�!$ԇJJ��Y��[���E�����w�ͽ;��,>��.S�hJ��8-��w��׻Ro+�������W�B;󓮋Ҵ�.���L�>�x����0\5ma�c�7!u~a��o���ۦ�5}]<��k��
o�J7��v9]ʴ����K���ie�8صfc���p�[��6e��]_��������_��悤[�Z��:-l�W�)����)C���
@(]^��������n��>:�;��kv9,�ٸ�+�\SA���$��Δf������ ���f�쫺ZC���&��
���7Mk�4������/�'?����5��-_o�QB($a�|��c$�i��w'+���l����������̈́2x�L8R_���ӭ�����Lejc���t�\����{3��*�䑛q6���tg�Y����)�y��(@���Iԑ�����l>�;}���}8��m~�����'ٷ@��[�e�1�,��E
.�L�����(�)*{����$&����u�x&�2��ΏG�8\�l��	!��xB"r�������%2�*�/��!��Ψʬm q$.a����3ӭ��:<�UF�bYB8��y S�y�:)��%2����!.�@"�S5�S��(�Y?(�Li��D�P/��8�D�^;{�<V8!
ɜ��/�&� �\p��ٱ*��C�f��34�����D�x&����l*=+@�~|8�@e��"��E.�$��!(�B��T}\���C
�ڻ��{�w��m[U>���	�_�{����Я�#�R�~������k#���Y̤���Ņ�ޢ��nf6�	'Hб4�L�W ��Z[_�?�<N<�G:t4���� tij�8N%Eđ��T=G���!��YK� ˡ(@M&-�$�*��      !     x�uV[n�0��O��D�����3��H��s���)z�)٢d������x�]t�}z��������cw�º:�wr�W��y�g	�z<_��=}~�����xsT�!�+e����H.B�p2h�b�Ő��-��#�Mъ_���*,����Ӂ�^�#�Y���e��0�����ޓh����W�],<,.g*%�z�� ��-^����b�~IׄB����~<��ZD�;)%��v�"9����Vډ |P��r�+��9�h���T��2�X/�Ԃ���+�����i�dٖ��˚O��+F?�U��jQ�B�1tj%LUs��4��j*��g壢}P��w�F�tj�!��C�)�J��cd�62mlئ.$�i��SM<����r��ӷl���G�x�v�opi�#�D��|�M��a���$��OU����m�T��8�Nq�F�B��i˭+Aųj���t�n�*�����DvD�;�D�~H��9�<gu�����G�nӖ34Xw����AY�+���sj�Jl�AI��+[�M$vý��f��b��"����x�eU&��C~!K�/��i}ba�
WG���JZ�ZUJ�13%�ZFJ���U���F%O�Pa���U�����&ѹ�z����Ks{f��,�	�p�(�r���ڰ�-���D���YF��u����Mm����D� �c���x��Mv�84�8�8׌�Ά�K�;�:Y|s��k��Z48_��Y���-��n�S0�4�^�6O͈7��yY����5�         Z   x�3�,��/�442�tt����2�L+M,�415�t���tu�2�LL����43�4�*3�LMI�46526E�3�,.-H-*-N-B21F��� M+�     