PGDMP         /                |            veterinaryManagement    15.5    15.5 K    H           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            I           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            J           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            K           1262    17272    veterinaryManagement    DATABASE     �   CREATE DATABASE "veterinaryManagement" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 &   DROP DATABASE "veterinaryManagement";
                postgres    false            �            1259    18133    animals    TABLE     �  CREATE TABLE public.animals (
    animal_id bigint NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_colour character varying(255) NOT NULL,
    animal_date_of_birth date,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    18132    animals_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.animals_animal_customer_id_seq;
       public          postgres    false    216            L           0    0    animals_animal_customer_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.animals_animal_customer_id_seq OWNED BY public.animals.animal_customer_id;
          public          postgres    false    215            �            1259    18131    animals_animal_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.animals_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    216            M           0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    214            �            1259    18145    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    appointment_animal_id integer NOT NULL,
    appointment_doctor_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    18143 &   appointments_appointment_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_animal_id_seq;
       public          postgres    false    220            N           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_animal_id_seq OWNED BY public.appointments.appointment_animal_id;
          public          postgres    false    218            �            1259    18144 &   appointments_appointment_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_doctor_id_seq;
       public          postgres    false    220            O           0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_doctor_id_seq OWNED BY public.appointments.appointment_doctor_id;
          public          postgres    false    219            �            1259    18142    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    220            P           0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    217            �            1259    18155    availabledates    TABLE     �   CREATE TABLE public.availabledates (
    available_date_id bigint NOT NULL,
    available_date date,
    abailable_date_doctor_id integer NOT NULL
);
 "   DROP TABLE public.availabledates;
       public         heap    postgres    false            �            1259    18154 +   availabledates_abailable_date_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.availabledates_abailable_date_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 B   DROP SEQUENCE public.availabledates_abailable_date_doctor_id_seq;
       public          postgres    false    223            Q           0    0 +   availabledates_abailable_date_doctor_id_seq    SEQUENCE OWNED BY     {   ALTER SEQUENCE public.availabledates_abailable_date_doctor_id_seq OWNED BY public.availabledates.abailable_date_doctor_id;
          public          postgres    false    222            �            1259    18153 $   availabledates_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.availabledates_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.availabledates_available_date_id_seq;
       public          postgres    false    223            R           0    0 $   availabledates_available_date_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.availabledates_available_date_id_seq OWNED BY public.availabledates.available_date_id;
          public          postgres    false    221            �            1259    18163 	   customers    TABLE     4  CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    18162    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    225            S           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    224            �            1259    18172    doctors    TABLE     &  CREATE TABLE public.doctors (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(255) NOT NULL
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    18171    doctors_doctor_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.doctors_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    227            T           0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    226            �            1259    18182    vaccines    TABLE       CREATE TABLE public.vaccines (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_protection_finish_date date,
    vaccine_protection_start_date date,
    vaccine_animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    18181    vaccines_vaccine_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.vaccines_vaccine_animal_id_seq;
       public          postgres    false    230            U           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.vaccines_vaccine_animal_id_seq OWNED BY public.vaccines.vaccine_animal_id;
          public          postgres    false    229            �            1259    18180    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    230            V           0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    228            �           2604    18136    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    216    214    216            �           2604    18137    animals animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animals ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animals_animal_customer_id_seq'::regclass);
 I   ALTER TABLE public.animals ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    18148    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    220    217    220            �           2604    18149 "   appointments appointment_animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_animal_id SET DEFAULT nextval('public.appointments_appointment_animal_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_animal_id DROP DEFAULT;
       public          postgres    false    218    220    220            �           2604    18150 "   appointments appointment_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_doctor_id SET DEFAULT nextval('public.appointments_appointment_doctor_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_doctor_id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    18158     availabledates available_date_id    DEFAULT     �   ALTER TABLE ONLY public.availabledates ALTER COLUMN available_date_id SET DEFAULT nextval('public.availabledates_available_date_id_seq'::regclass);
 O   ALTER TABLE public.availabledates ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    221    223    223            �           2604    18159 '   availabledates abailable_date_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.availabledates ALTER COLUMN abailable_date_doctor_id SET DEFAULT nextval('public.availabledates_abailable_date_doctor_id_seq'::regclass);
 V   ALTER TABLE public.availabledates ALTER COLUMN abailable_date_doctor_id DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    18166    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    224    225    225            �           2604    18175    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    227    226    227            �           2604    18185    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    228    230    230            �           2604    18186    vaccines vaccine_animal_id    DEFAULT     �   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_animal_id SET DEFAULT nextval('public.vaccines_vaccine_animal_id_seq'::regclass);
 I   ALTER TABLE public.vaccines ALTER COLUMN vaccine_animal_id DROP DEFAULT;
       public          postgres    false    229    230    230            7          0    18133    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_breed, animal_colour, animal_date_of_birth, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    216   _       ;          0    18145    appointments 
   TABLE DATA           v   COPY public.appointments (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    220   �_       >          0    18155    availabledates 
   TABLE DATA           e   COPY public.availabledates (available_date_id, available_date, abailable_date_doctor_id) FROM stdin;
    public          postgres    false    223   X`       @          0    18163 	   customers 
   TABLE DATA              COPY public.customers (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    225   �`       B          0    18172    doctors 
   TABLE DATA           q   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    227   �a       E          0    18182    vaccines 
   TABLE DATA           �   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_finish_date, vaccine_protection_start_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    230   $b       W           0    0    animals_animal_customer_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.animals_animal_customer_id_seq', 1, false);
          public          postgres    false    215            X           0    0    animals_animal_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.animals_animal_id_seq', 16, true);
          public          postgres    false    214            Y           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_animal_id_seq', 1, false);
          public          postgres    false    218            Z           0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_doctor_id_seq', 1, false);
          public          postgres    false    219            [           0    0    appointments_appointment_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 10, true);
          public          postgres    false    217            \           0    0 +   availabledates_abailable_date_doctor_id_seq    SEQUENCE SET     Z   SELECT pg_catalog.setval('public.availabledates_abailable_date_doctor_id_seq', 1, false);
          public          postgres    false    222            ]           0    0 $   availabledates_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.availabledates_available_date_id_seq', 13, true);
          public          postgres    false    221            ^           0    0    customers_customer_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.customers_customer_id_seq', 15, true);
          public          postgres    false    224            _           0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 7, true);
          public          postgres    false    226            `           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.vaccines_vaccine_animal_id_seq', 1, false);
          public          postgres    false    229            a           0    0    vaccines_vaccine_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 259, true);
          public          postgres    false    228            �           2606    18141    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    216            �           2606    18152    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    220            �           2606    18161 "   availabledates availabledates_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.availabledates
    ADD CONSTRAINT availabledates_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.availabledates DROP CONSTRAINT availabledates_pkey;
       public            postgres    false    223            �           2606    18170    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    225            �           2606    18179    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    227            �           2606    18192 &   customers uk_5vhox5jsqitujs1k7bcsb2rj8 
   CONSTRAINT     j   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_5vhox5jsqitujs1k7bcsb2rj8 UNIQUE (customer_mail);
 P   ALTER TABLE ONLY public.customers DROP CONSTRAINT uk_5vhox5jsqitujs1k7bcsb2rj8;
       public            postgres    false    225            �           2606    18198 $   doctors uk_7s4l7559eox2hor73b3qp3vq2 
   CONSTRAINT     g   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT uk_7s4l7559eox2hor73b3qp3vq2 UNIQUE (doctor_phone);
 N   ALTER TABLE ONLY public.doctors DROP CONSTRAINT uk_7s4l7559eox2hor73b3qp3vq2;
       public            postgres    false    227            �           2606    18196 $   doctors uk_amsyrdrr2f0d48ciy29o9hvjm 
   CONSTRAINT     f   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT uk_amsyrdrr2f0d48ciy29o9hvjm UNIQUE (doctor_mail);
 N   ALTER TABLE ONLY public.doctors DROP CONSTRAINT uk_amsyrdrr2f0d48ciy29o9hvjm;
       public            postgres    false    227            �           2606    18194 &   customers uk_bn3wq4vhuqco545y52xp96gqd 
   CONSTRAINT     k   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_bn3wq4vhuqco545y52xp96gqd UNIQUE (customer_phone);
 P   ALTER TABLE ONLY public.customers DROP CONSTRAINT uk_bn3wq4vhuqco545y52xp96gqd;
       public            postgres    false    225            �           2606    18190    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    230            �           2606    18209 '   appointments fk27d0xcbajwaeeun2doojsae4    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk27d0xcbajwaeeun2doojsae4 FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctors(doctor_id);
 Q   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk27d0xcbajwaeeun2doojsae4;
       public          postgres    false    220    227    3227            �           2606    18214 *   availabledates fk837khttdm4laml7s7ig4yr42x    FK CONSTRAINT     �   ALTER TABLE ONLY public.availabledates
    ADD CONSTRAINT fk837khttdm4laml7s7ig4yr42x FOREIGN KEY (abailable_date_doctor_id) REFERENCES public.doctors(doctor_id);
 T   ALTER TABLE ONLY public.availabledates DROP CONSTRAINT fk837khttdm4laml7s7ig4yr42x;
       public          postgres    false    227    3227    223            �           2606    18219 $   vaccines fkekhfjmpsduds8nnilqe9b467v    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkekhfjmpsduds8nnilqe9b467v FOREIGN KEY (vaccine_animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkekhfjmpsduds8nnilqe9b467v;
       public          postgres    false    3215    216    230            �           2606    18199 #   animals fknjsvd8kplxqmf48ybxayrx6ru    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru FOREIGN KEY (animal_customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru;
       public          postgres    false    216    3221    225            �           2606    18204 (   appointments fko4t945rb708af9ry6syof7bwt    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fko4t945rb708af9ry6syof7bwt FOREIGN KEY (appointment_animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fko4t945rb708af9ry6syof7bwt;
       public          postgres    false    220    3215    216            7   �   x���Y� ��g�^l����/�5RHj\�{pa����		<|9�A�Y�*C:��m	��4(is�6R��7�?ei[���맨
���t��scl��h���3�T8��B7�7�U=��x�89)\׿-֥&�V�N/��އ�kٱ��Ѣ�f񘪗�W�wJyદQ!�c�0:I0>I#�] ��ע�      ;   _   x�U���0ѳ]E0�v��B�u#��iF�`E�E�PG� HI�~���i���*(��+ �:)��ܣ�6ɻ@����C<"hB_$|��`��Q$`      >   Z   x�E��	�0�︋����]��U�Gx���P�d��6=iEg��*��C=)~�&M�pUv��.v6�Ѭ��k�"a�jQ�!��!�8�=      @   �   x�]Ͻ� ����;>ʦ�nNut�D$M)CK�w��,������?\�nv��P�k�xr#��]וIJ��;?)P~���]�e�TZs�z���(Pjc���q^B�S�zJQ(�#׵���5��L�f��l��z�A�2��b�X�H��i����rT��Ǭ��P���9�H{q      B   �   x�m�=1�9�1�������V��9�+���7���޷�t!�8s���ו���J{��@�
F^�3�(1qãT��ȭ	N1�9��%�6>��-�����_hds�C�XfEj�^�Z�u�[�����ɉ@�<����K�      E   �  x���K��6 ��_���c��zwmd?
襇�
��_9�C�.���[r8�)JQ��xy��s:���ן�`^���g�>��t�|���O�l@���p���Cx����q�N���@���va`�Yh,���,��~>_^��_��j��5!5]���tݧK��	�"%DA����%=]?�_ߦ��끘��ը�d���, �������c:��� R�}�}�P?�ܴ?�����z�~ܰұs9g��n��tx{���g��D))C��A������s����;U�p���5�)�<����zx}�l�fv �Z ԡҘ�4�tP���I���t�W���:�bߎ�:�b�0)7��N�w�~���ƈ�:F/�Mui�*�>�����sT\�A\Ţwz���cZ�Ǎ�ٝV��q%��pH�p8P�Ԏ]��D-�EKf��ʟ鞯���dk��������u�����!Z���/i�tk0}o+T��{���ym�Q��a�b�A�s+�LB!f�I�������"�q�6ujg� r�1�D&���	�sD�L�	Lh6��:�-��P�nn$�ԕb��=��$Q�۷�/X���S�E
Ƃ4�ځ{6Z���p�1�ۿ�*�t������؁�n݀#����	�EF�l�6��k
<]SM���"�([J���V���R�!��G��:�I��a�9Xa��#�%3�33��~����9��Ӭ�h̖E]��@̭0h����O�gl�g�J���dl�?wF�s��b5J��YŢ!�
�")-��l(W�t��n9�ފ�Gn�L{'���G���严Йzo/�|�L&Jلb�,"򹷪��ȸ+�eGEM
����渘��Ʊ�d�4�M���=��BV"ߴ�����GY�àpђ�γ�r��_9w��f%s�9*ݢ�0��nQ�>���iO�
�`��uư1ld�-Gd9"�q����8���zTs���w��MR���W$Sn�A��i�w]��yf�2���"�\�Y.����dt;�VdA����D+Q)���)�-K��\������Y�Ȕ����"6�����1կ��ȜDkqy�֢�K?��E)��,���9��"�"��7����n�o�n����Z ����i��6�-$ֽ���U�ɹ�yv�9?��.4W����}v�lE�g��b�J���������� ��^�\u垝���5DC&e�VД���x����,ȼ���I�,>�����|J�����g�H?�OD��O?��V��l"s�&���^���D�v����\�/�L��c��������,Q`�+{?�ifP���X�܁��[X\Df�˺�PY����r-Z*���l)���f�Ȋ�N*>m*)'���sY�jN���J����7�K���+��dx*Z|C�Ss ��xʍ3-Hj�IՖ�
3������J@����4�/8�`�� �_�0� �Ŝ��y��X.�$J��e1!y҇�zg���t�F3���D�Q���3�V}nj[���D����hs&�����`�]�m?�<�0�Z'U�Dϴ��t���in��+α��u)�BV��(T�r^��AK���]�~@��h���x�-r�8�Ӯ�E����N�D�QM��%~}�'��@�.O�U���#���􂅑Ef��8��[���̠O���ME��>6��'1!�-En)Ԗ���^g����,��-���{��?F��'(�Uqa�Z�9[Y��������?�� %C3<�C�0�6�C�Z����R$&�kgXd+GJy�E��d%ϱ��#�p�F��Y�"�V�2�3�JTf|�0�����g*d�@?���S�I	o��6���{PTnP����/�L[&���(m��1w�:�A;����(?\Zo�3�9�S��Lź�ZACL vG����>���bx���bEy	ߟ�8�EXP���v�� 	S�b     